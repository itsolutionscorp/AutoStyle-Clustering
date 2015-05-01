#/!usr/bin/python -tt

import sys
import time
import ast
import betterast
import zss
import re
from multiprocessing import Pool


def get_node_label(node):
  return str(type(node)).replace("<class '_ast.","").replace("'>","")


def build_tree(node, ast_node, functions):
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

def generate_ast(filename):
  try:
    ast_node = ast.parse(open(filename, 'r').read(), mode='exec')
  except Exception as e:
    print "FAILED: " + filename + "  error: " + str(e)
    return
  ast_node = ast.fix_missing_locations(ast_node)
  b_node = betterast.Node(get_node_label(ast_node))
  return build_tree(b_node,ast_node,{})

def natural_sort(l): 
    '''
    Sorts numbered filenames by their integer value 
    Ref: http://blog.codinghorror.com/sorting-for-humans-natural-sort-order/
    '''    
    convert = lambda text: int(text) if text.isdigit() else text.lower() 
    alphanum_key = lambda key: [ convert(c) for c in re.split('([0-9]+)', key) ] 
    return sorted(l, key = alphanum_key)

def compute(t):
  try:
    return zss.simple_distance(t[0], t[1])
  except Exception as e:
    print e
    return 0.0

def compute_edit_distances(home_dir,n):
  # Construct trees
  nthtree = generate_ast(home_dir + "/src/"+str(n)+".py")
  start_time = time.time()
  treeTuples = []
  for i in range(0, n):
    treeTuples.append((generate_ast(home_dir + "/src/"+str(i)+".py"),nthtree))

  # Construct tree tuples
  dist = []
  pool = Pool()
  dist = pool.map(compute, treeTuples)
  print("--- %s seconds ---" % str(time.time() - start_time))
  return dist


if __name__ == '__main__':
  if len(sys.argv) != 3:
    print >> sys.stderr, "Usage: python_newsubmission_edit_dist.py home_dir new_submission_index"
    exit(-1)
  start_time = time.time()
  home_dir = sys.argv[1].rstrip("/")
  n = int(sys.argv[2])
  compute_edit_distances(home_dir, n)
  print("--- %s seconds ---" % str(time.time() - start_time))


