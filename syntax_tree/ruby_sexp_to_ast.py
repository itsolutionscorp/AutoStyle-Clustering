import glob
import tree
import sys

def build_string(tree_node):
	str_rep = "{" + str(tree_node.label) + " "
	for kid in tree_node.children:
		str_rep += "{" + build_string(kid) + "}"
	str_rep += "}"
	return str_rep

def main(home_dir, func_name):
	sexp_files = glob.glob(home_dir+"/ast/*ast")
	for f in sexp_files:
		fname = f.split("/")[-1]
		ast = tree.abstract_syntax_tree(f, func_name)
		with open(home_dir + "/jast/" + fname, "w") as ff:
			ff.write(build_string(ast))

if __name__ == "__main__":
	if len(sys.argv) != 3:
		print sys.stderr, "Usage: ruby_sexp_to_ast.py <home_dir> <function name>"
		exit(-1)
	else:
		main(sys.argv[1], sys.argv[2])