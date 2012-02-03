package com.wtfrak.nixon.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.wtfrak.nixon.interpreter.names.DefineName;
import com.wtfrak.nixon.interpreter.names.Name;
import com.wtfrak.nixon.interpreter.names.WriteName;
import com.wtfrak.nixon.parser.expressions.Expression;
import com.wtfrak.nixon.parser.expressions.ExpressionType;

public class Task implements Context, Runnable {
	private static Task rootTask = new Task();
	
	public static Task getRootTask() {
		return rootTask;
	}
	
	private Task parentTask;
	private List<Expression> expressions;
	private HashMap<String, Name> scope;
	private List<Task> childTasks;
	private List<Thread> childThreads;
	private Stack<Expression> stack;

	private Task() {
		this.parentTask = null;
		this.expressions = new ArrayList<Expression>();
		this.scope = new HashMap<String, Name>();
		this.childTasks = new ArrayList<Task>();
		this.childThreads = new ArrayList<Thread>();
		this.stack = new Stack<Expression>();

		// TODO Find a better place to do this scope initialisation.
		this.scope.put("define", new DefineName());
		this.scope.put("write", new WriteName());
	}

	public Task(Task parentTask) {
		this.parentTask = parentTask;
		this.expressions = new ArrayList<Expression>();
		this.scope = new HashMap<String, Name>();
		this.childTasks = new ArrayList<Task>();
		this.childThreads = new ArrayList<Thread>();
		this.stack = new Stack<Expression>();
	}
	
	public void addExpressions(List<Expression> expressions) {
		this.expressions.addAll(expressions);
	}

	public Stack<Expression> getStack() {
		return this.stack;
	}

	public HashMap<String, Name> getScope() {
		return this.scope;
	}

	public void run() {
		while (!this.expressions.isEmpty()) {
			Expression expression = this.expressions.remove(0);
			this.stack.push(expression);

			while (expression.getExpressionType() == ExpressionType.COMMAND) {
				this.stack.pop(); // Remove command expression.

				try {
					evaluate();
				} catch (InterpreterException e) {
					e.printStackTrace();
					System.exit(1);
				}

				if (!this.stack.isEmpty()) {
					expression = this.stack.peek();
				} else {
					break;
				}
			}
		}

		if (hasParent()) {
			this.parentTask.removeTask(this);
		}
		
		if (this.childThreads.size() > 0) {
			for (Thread childThread : this.childThreads) {
				if (childThread.isAlive()) {
					try {
						childThread.join();
					} catch (InterruptedException e) {
						
					}
				}
			}
		}
	}

	private void evaluate() throws InterpreterException {
		Expression expression = this.stack.pop();

		switch (expression.getExpressionType()) {

		case TASK:
			Task childTask = new Task(this);
			childTask.addExpressions(expression.getExpressions());
			addTask(childTask);
			break;

		case LIST:
			for (Expression expr : expression.getExpressions()) {
				if (expr.getExpressionType() == ExpressionType.COMMAND) {
					evaluate();
				} else {
					this.stack.push(expr);
				}
			}
			break;

		case STRING:
			this.stack.push(expression);
			break;

		case NUMBER:
			this.stack.push(expression);
			break;

		case NAME:
			runName((String) expression.getValue());
			break;

		}
	}

	private synchronized void addTask(Task task) {
		this.childTasks.add(task);
		Thread childThread = new Thread(task);
		childThread.start();
		this.childThreads.add(childThread);
	}

	private synchronized void removeTask(Task task) {
		this.childTasks.remove(task);
	}

	private boolean hasParent() {
		return this.parentTask != null;
	}

	private Name findName(String nameKey) throws InterpreterException {
		if (this.scope.containsKey(nameKey)) {
			return this.scope.get(nameKey);
		} else if (hasParent()) {
			return this.parentTask.findName(nameKey);
		} else {
			throw new InterpreterException();
		}
	}

	private void runName(String nameKey) throws InterpreterException {
		Name name = findName(nameKey);
		name.evaluate(this);
	}
}
