"""
Filename: python_ast.py
About: Generates an AST for python code
Author: Rohan Roy Choudhury
"""

import os
import ast
import sys
import betterast 
from collections import defaultdict

def get_node_label(node):
	"""
	@type node: ast.Module object
	@param node: The root node of the AST
	
	@rtype: String
	@return: The label of the node in String format
	"""
	return str(type(node)).replace("<class '_ast.","").replace("'>","")


def build_tree(node, ast_node, functions):
	"""
	@type ast_node: ast.Module object
	@param ast_node: The root node of the AST
	
	@type node: betterast.Node object
	@param node: The root node of the AST in betterast format
	
	@rtype: betterast.Node object
	@return: The root of the betterast format tree
	"""
	if isinstance(ast_node, str) or isinstance(ast_node, int) or (ast_node == None):
		return node
	n = [i for i in ast.iter_child_nodes(ast_node)]
	for i,kid in enumerate(n):
		label = get_node_label(kid)
		if label == "FunctionDef":
			name = [r[1] for r in ast.iter_fields(kid) if r[0] == 'name'][0]
			newname = 'function' + str(len(functions))
			functions[name] = newname
			label = "FunctionDef: " + newname
		if i==0 and node.label == "Call" and 'func' in dir(ast_node) :
			label = [r[1] for r in ast.iter_fields(kid) if r[0] == 'attr' or r[0] == 'id'][0]
		if label in functions:
			label = functions[label]
		kidNode = betterast.Node(label)
		node.addkid(kidNode)
		build_tree(kidNode, kid, functions)
	return node

def generate_ast(filename, print_ast, home_directory):
	"""
	@type filename: String
	@param filename: Name of the python file for which the function should generate an AST (include path if the file is not in this script's directory)
	
	@rtype: None  
	@return: None
	"""
	try:
		ast_node = ast.parse(open(filename, 'r').read(), mode='exec')
	except Exception as e:
		print "FAILED: " + filename + "  error: " + str(e)
		return
	ast_node = ast.fix_missing_locations(ast_node)
	b_node = betterast.Node(get_node_label(ast_node))
	tree = build_tree(b_node,ast_node,{})
	name = filename.split("/")
	open(home_directory+ "/ast_text/" +name[-1].strip(".py") + '.txt','w').close()
	f_txt = open(home_directory+ "/ast_text/" +name[-1].strip(".py") + '.txt','w')
	f_txt.writelines(tree.__str__())
	f_txt.close()
	if print_ast:
		f_dot = open(home_directory+ "/ast/" +name[-1].strip(".py") + '.dot','w')
		f_dot.write(tree.dotty())
		f_dot.close()


if __name__ == '__main__':
	if os.path.isdir(sys.argv[1].rstrip("/")):
		home_directory = sys.argv[1].rstrip("/")
		directory = home_directory + "/code"
		files = [directory+"/"+f for f in os.listdir(directory) if (not f.startswith('.') and not os.path.isdir(directory+"/"+f))]
		for f in files:
			generate_ast(f,1, home_directory)
		print "Successfully completed!"
	else:
		for item in sys.argv[1:]:
			generate_ast(item, 1, ".")

#FOR PREPROCESSING
# assign same name for all variables (e.g var)
# change argument name in function declaratiosn the same - e.g f(arg, arg, arg)  - these won't be used since local variables are called var
# for string/int/float etc, change it from "abc" or "54" to string-literal  or int-literal or float-literal
# change value of regular expression to "regex

#OLD CODE (no longer used, left here for reference)

# from astmonkey import visitors, transformers  #pip install astmonkey, pip install pydot

# def compose_list(mylist, node):
# 	if isinstance(node, str) or isinstance(node, int) or (node == None):
# 		return mylist
# 	for field, value in ast.iter_fields(node):
# 		mylist.append((node, value))
# 		if isinstance(value, list):
# 			for item in value:
# 				mylist = compose_list(mylist, item)
# 		else:
# 			mylist = compose_list(mylist, value)
# 	return mylist


# def generate_image(node, name):
# 	"""
# 	@type node: ast.Module object
# 	@param node: The AST for which this function should generate an image

# 	@type name: String
# 	@param name: Name of the output png file
	
# 	@rtype: None
# 	@return: None
# 	"""
# 	graph = transformers.ParentNodeTransformer().visit(node)
# 	visitor = visitors.GraphNodeVisitor()
# 	visitor.visit(graph)
# 	visitor.graph.write_png(name)
