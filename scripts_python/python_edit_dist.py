"""
Filename: python_ast.py
About: Generates an AST for python code
Author: Rohan Roy Choudhury
"""
from python_ast import generate_ast
import sys
import zss

def get_edit_distance(file1, file2):
	"""
	@type file1: String
	@param file1: Name (including path) of the first python file 
	
	@type file2: String
	@param file2: Name (including path) of the second python file
	
	@rtype: betterast.Node object
	@return: The root of the betterast format tree
	"""
	tree1 = generate_ast(file1, 0)
	tree2 = generate_ast(file2, 0)
	print "Computing edit distance..."
	dist = zss.simple_distance(A=tree1,B=tree2)
	print dist
	return dist

if __name__ == '__main__': 
	if len(sys.argv) != 3:
		print "Usage: python_edit_dist.py <file1> <file2>"
		exit(0)
	get_edit_distance(sys.argv[1], sys.argv[2])