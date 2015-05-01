#/!usr/bin/python -tt

import sys
import ast
import glob
import os
import time
from multiprocessing import Pool
from string import digits

def dump_ast(filenames):
  for filename in filenames:
    try:
      ast_node = ast.parse(open(filename, 'r').read(), mode='exec')
    except Exception as e:
      print "FAILED: " + filename + "  error: " + str(e)
      return
    ast_node = ast.fix_missing_locations(ast_node)
    f = open(filename.replace("/src/","/ast_dump/").replace(".py",".ast"), "w")
    #replace('\n', ' ').replace('\r', '').translate(None, digits)
    f.write(ast.dump(ast_node, annotate_fields=False).replace(")","}").replace("(","{").replace(":",""))

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
  # compute_edit_dist(home_dir)


