/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jdt.core.dom;

import java.util.List;

/**
 * Method invocation expression AST node type.
 * For JLS2:
 * <pre>
 * MethodInvocation:
 *     [ Expression <b>.</b> ] Identifier 
 *         <b>(</b> [ Expression { <b>,</b> Expression } ] <b>)</b>
 * </pre>
 * For JLS3, type arguments are added:
 * <pre>
 * MethodInvocation:
 *     [ Expression <b>.</b> ]  
 *         [ <b>&lt;</b> Type { <b>,</b> Type } <b>&gt;</b> ]
 *         Identifier <b>(</b> [ Expression { <b>,</b> Expression } ] <b>)</b>
 * </pre>
 * 
 * @since 2.0
 */
public class MethodInvocation extends Expression {
	
	/**
	 * The "expression" structural property of this node type.
	 * @since 3.0
	 */
	public static final ChildPropertyDescriptor EXPRESSION_PROPERTY = 
		new ChildPropertyDescriptor(MethodInvocation.class, "expression", Expression.class, OPTIONAL, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "typeArguments" structural property of this node type (added in JLS3 API).
	 * @since 3.0
	 */
	public static final ChildListPropertyDescriptor TYPE_ARGUMENTS_PROPERTY = 
		new ChildListPropertyDescriptor(MethodInvocation.class, "typeArguments", Type.class, NO_CYCLE_RISK); //$NON-NLS-1$
	
	/**
	 * The "name" structural property of this node type.
	 * @since 3.0
	 */
	public static final ChildPropertyDescriptor NAME_PROPERTY = 
		new ChildPropertyDescriptor(MethodInvocation.class, "name", SimpleName.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "arguments" structural property of this node type.
	 * @since 3.0
	 */
	public static final ChildListPropertyDescriptor ARGUMENTS_PROPERTY = 
		new ChildListPropertyDescriptor(MethodInvocation.class, "arguments", Expression.class, CYCLE_RISK); //$NON-NLS-1$
	
	/**
	 * A list of property descriptors (element type: 
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 * @since 3.0
	 */
	private static final List PROPERTY_DESCRIPTORS_2_0;
	
	/**
	 * A list of property descriptors (element type: 
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 * @since 3.0
	 */
	private static final List PROPERTY_DESCRIPTORS_3_0;
	
	static {
		createPropertyList(MethodInvocation.class);
		addProperty(EXPRESSION_PROPERTY);
		addProperty(NAME_PROPERTY);
		addProperty(ARGUMENTS_PROPERTY);
		PROPERTY_DESCRIPTORS_2_0 = reapPropertyList();
		
		createPropertyList(MethodInvocation.class);
		addProperty(EXPRESSION_PROPERTY);
		addProperty(TYPE_ARGUMENTS_PROPERTY);
		addProperty(NAME_PROPERTY);
		addProperty(ARGUMENTS_PROPERTY);
		PROPERTY_DESCRIPTORS_3_0 = reapPropertyList();
	}

	/**
	 * Returns a list of structural property descriptors for this node type.
	 * Clients must not modify the result.
	 * 
	 * @param apiLevel the API level; one of the
	 * <code>AST.JLS&ast;</code> constants

	 * @return a list of property descriptors (element type: 
	 * {@link StructuralPropertyDescriptor})
	 * @since 3.0
	 */
	public static List propertyDescriptors(int apiLevel) {
		if (apiLevel == AST.JLS2) {
			return PROPERTY_DESCRIPTORS_2_0;
		} else {
			return PROPERTY_DESCRIPTORS_3_0;
		}
	}
			
	/**
	 * The expression; <code>null</code> for none; defaults to none.
	 */
	private Expression optionalExpression = null;
	
	/**
	 * The type arguments (element type: <code>Type</code>). 
	 * Null in JLS2. Added in JLS3; defaults to an empty list
	 * (see constructor).
	 * @since 3.0
	 */
	private ASTNode.NodeList typeArguments = null;

	/**
	 * The method name; lazily initialized; defaults to a unspecified,
	 * legal Java method name.
	 */
	private SimpleName methodName = null;
	
	/**
	 * The list of argument expressions (element type: 
	 * <code>Expression</code>). Defaults to an empty list.
	 */
	private ASTNode.NodeList arguments =
		new ASTNode.NodeList(ARGUMENTS_PROPERTY);

	/**
	 * Creates a new AST node for a method invocation expression owned by the 
	 * given AST. By default, no expression, no type arguments, 
	 * an unspecified, but legal, method name, and an empty list of arguments.
	 * 
	 * @param ast the AST that is to own this node
	 */
	MethodInvocation(AST ast) {
		super(ast);	
		if (ast.apiLevel >= AST.JLS3) {
			this.typeArguments = new ASTNode.NodeList(TYPE_ARGUMENTS_PROPERTY);
		}
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	final List internalStructuralPropertiesForType(int apiLevel) {
		return propertyDescriptors(apiLevel);
	}
	
	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == NAME_PROPERTY) {
			if (get) {
				return getName();
			} else {
				setName((SimpleName) child);
				return null;
			}
		}
		if (property == EXPRESSION_PROPERTY) {
			if (get) {
				return getExpression();
			} else {
				setExpression((Expression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}
	
	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	final List internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == ARGUMENTS_PROPERTY) {
			return arguments();
		}
		if (property == TYPE_ARGUMENTS_PROPERTY) {
			return typeArguments();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	final int getNodeType0() {
		return METHOD_INVOCATION;
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	ASTNode clone0(AST target) {
		MethodInvocation result = new MethodInvocation(target);
		result.setSourceRange(this.getStartPosition(), this.getLength());
		result.setName((SimpleName) getName().clone(target));
		result.setExpression(
			(Expression) ASTNode.copySubtree(target, getExpression()));
		if (this.ast.apiLevel >= AST.JLS3) {
			result.typeArguments().addAll(ASTNode.copySubtrees(target, typeArguments()));
		}
		result.arguments().addAll(ASTNode.copySubtrees(target, arguments()));
		return result;
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	final boolean subtreeMatch0(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	void accept0(ASTVisitor visitor) {
		boolean visitChildren = visitor.visit(this);
		if (visitChildren) {
			// visit children in normal left to right reading order
			acceptChild(visitor, getExpression());
			if (this.ast.apiLevel >= AST.JLS3) {
				acceptChildren(visitor, this.typeArguments);
			}
			acceptChild(visitor, getName());
			acceptChildren(visitor, this.arguments);
		}
		visitor.endVisit(this);
	}
	
	/**
	 * Returns the expression of this method invocation expression, or 
	 * <code>null</code> if there is none.
	 * 
	 * @return the expression node, or <code>null</code> if there is none
	 */ 
	public Expression getExpression() {
		return this.optionalExpression;
	}
	
	/**
	 * Sets or clears the expression of this method invocation expression.
	 * 
	 * @param expression the expression node, or <code>null</code> if 
	 *    there is none
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 */ 
	public void setExpression(Expression expression) {
		ASTNode oldChild = this.optionalExpression;
		preReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
		this.optionalExpression = expression;
		postReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
	}

	/**
	 * Returns the live ordered list of type arguments of this method
	 * invocation (added in JLS3 API).
	 * <p>
	 * Note: This API element is only needed for dealing with Java code that uses
	 * new language features of J2SE 1.5. It is included in anticipation of J2SE
	 * 1.5 support, which is planned for the next release of Eclipse after 3.0, and
	 * may change slightly before reaching its final form.
	 * </p>
	 * 
	 * @return the live list of type arguments
	 *    (element type: <code>Type</code>)
	 * @exception UnsupportedOperationException if this operation is used in
	 * a JLS2 AST
	 * @since 3.0
	 */ 
	public List typeArguments() {
		// more efficient than just calling unsupportedIn2() to check
		if (this.typeArguments == null) {
			unsupportedIn2();
		}
		return this.typeArguments;
	}
	
	/**
	 * Returns the name of the method invoked in this expression.
	 * 
	 * @return the method name node
	 */ 
	public SimpleName getName() {
		if (this.methodName == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.methodName == null) {
					preLazyInit();
					this.methodName = new SimpleName(this.ast);
					postLazyInit(this.methodName, NAME_PROPERTY);
				}
			}
		}
		return this.methodName;
	}
	
	/**
	 * Sets the name of the method invoked in this expression to the
	 * given name.
	 * 
	 * @param name the new method name
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * </ul>
	 */ 
	public void setName(SimpleName name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.methodName;
		preReplaceChild(oldChild, name, NAME_PROPERTY);
		this.methodName = name;
		postReplaceChild(oldChild, name, NAME_PROPERTY);
	}

	/**
	 * Returns the live ordered list of argument expressions in this method
	 * invocation expression.
	 * 
	 * @return the live list of argument expressions 
	 *    (element type: <code>Expression</code>)
	 */ 
	public List arguments() {
		return this.arguments;
	}

	/**
	 * Resolves and returns the binding for the method invoked by this
	 * expression.
	 * <p>
	 * Note that bindings are generally unavailable unless requested when the
	 * AST is being built.
	 * </p>
	 *
	 * @return the method binding, or <code>null</code> if the binding cannot
	 * be resolved
	 * @since 2.1
	 */
	public IMethodBinding resolveMethodBinding() {
		return this.ast.getBindingResolver().resolveMethod(this);
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	int memSize() {
		// treat Code as free
		return BASE_NODE_SIZE + 4 * 4;
	}
	
	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	int treeSize() {
		return 
			memSize()
			+ (this.optionalExpression == null ? 0 : getExpression().treeSize())
			+ (this.typeArguments == null ? 0 : this.typeArguments.listSize())
			+ (this.methodName == null ? 0 : getName().treeSize())
			+ (this.arguments == null ? 0 : this.arguments.listSize());
	}
}

