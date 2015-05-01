#/!usr/bin/python -tt

import sys
import ast
import glob
import betterast
import os
import time
from multiprocessing import Pool
from string import digits

def get_node_label(node):
  """
  return the label of the node in string format
  """
  return str(type(node)).replace("<class '_ast.","").replace("'>","")


def build_tree(node, ast_node, functions):
  """
  Return the root of the betterast format tree
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
      label = "FunctionDef" + newname
    if i==0 and node.label == "Call" and 'func' in dir(ast_node) :
      label = [r[1] for r in ast.iter_fields(kid) if r[0] == 'attr' or r[0] == 'id'][0]
    if label in functions:
      label = functions[label]
    kidNode = betterast.Node(label)
    node.addkid(kidNode)
    build_tree(kidNode, kid, functions)
  return node

def build_string(tree_node):
    str_rep = "{" + tree_node.label + " "
    for kid in tree_node.children:
            str_rep += "{" + build_string(kid) + "}"
    str_rep += "}"
    return str_rep

def dump_ast(filenames):
  for filename in filenames:
    try:
      ast_node = ast.parse(open(filename, 'r').read(), mode='exec')
    except Exception as e:
      print "FAILED: " + filename + "  error: " + str(e)
      return
    ast_node = ast.fix_missing_locations(ast_node)
    b_node = betterast.Node(get_node_label(ast_node))
    tree = build_tree(b_node,ast_node,{})
    f = open(filename.replace("/src/","/ast_dump/").replace(".py",".ast"), "w")
    #replace('\n', ' ').replace('\r', '').translate(None, digits)
    #ast.dump(ast_node, annotate_fields=False).replace("]","}").replace("[","{").replace(":","")
    f.write(build_string(tree))

def dump_single_ast(filename, code):
    try:
      ast_node = ast.parse(code, mode='exec')
    except Exception as e:
      print "FAILED: " + filename + "  error: " + str(e)
      raise Exception
    ast_node = ast.fix_missing_locations(ast_node)
    b_node = betterast.Node(get_node_label(ast_node))
    tree = build_tree(b_node,ast_node,{})
    with open(filename, "w") as f:
      f.write(build_string(tree))
    try:
      os.remove(home_dir + "/ast_dump/.DS_Store")
    except:
      pass

def compute(t):
  os.system(t)

def compute_edit_dist(home_dir):
  home_dir = home_dir.rstrip("/")
  files = glob.glob(home_dir+'/ast_dump/*')
  command = "java -jar " + os.path.abspath(home_dir + "/../../../syntax_tree/TEDjava/RTED.jar -f ") + files[-1] + " "
  start_time = time.time()
  dist = []
  pool = Pool()
  dist = pool.map(compute, [command + f for f in files])
  print("--- %s seconds ---" % str(time.time() - start_time))
  print dist
  
  

if __name__ == '__main__':
  if len(sys.argv) != 2:
    print >> sys.stderr, "Usage: python_ast_dump.py home_dir"
    exit(-1)
  home_dir = sys.argv[1].rstrip("/")
  dump_ast(glob.glob(home_dir+'/src/*'))
  try:
    os.remove(home_dir + "/ast_dump/.DS_Store")
  except:
    pass
  # compute_edit_dist(home_dir)


