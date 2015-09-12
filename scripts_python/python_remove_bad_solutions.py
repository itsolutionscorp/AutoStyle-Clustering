import sys
import glob
import re
import os

def check(language, assignment, func_name):
  directory = "../assignments/" + language + "/" + assignment + "/src/"
  files = glob.glob(directory + "*.py")
  wrong_names = []
  for f in files:
    with open(f, "r") as ff:
      code = "".join(ff.readlines())
      if re.search("def *" + func_name + " *\( *\w+ *\, *\w+ *\) *\:", code) == None:
        wrong_names.append(f)
  print wrong_names
  print len(wrong_names)

def run_test_anagrams(language, assignment, func_name):
  directory = "../assignments/" + language + "/" + assignment + "/src/"
  sys.path.insert(1, os.path.abspath(directory))
  files = glob.glob(directory + "*.py")
  for f in files:
    thisfile = os.path.basename(f).rstrip(".py")
    filemodule = __import__(thisfile)  
    try:
      output = eval("filemodule." + func_name + '("listen", ["enlists" ,"google" ,"inlets" ,"banana"])')
    except:
      print "----------------- Exception: failed " + thisfile
      output = None
    if output != ['inlets'] and output != 'inlets':
      print "----------------- Wrong: " + thisfile, output
    os.system("rm " + thisfile + ".pyc")

def run_test_series(language, assignment, func_name):
  directory = "../assignments/" + language + "/" + assignment + "/src/"
  sys.path.insert(1, os.path.abspath(directory))
  files = glob.glob(directory + "*.py")
  for f in files:
    thisfile = os.path.basename(f).rstrip(".py")
    try:
      if thisfile not in ['233', '134', '340']:
        filemodule = __import__(thisfile)  
        output = eval("filemodule." + func_name + '("97867564", 3)')
    except Exception as e:
      print "----------------- Exception " + str(e) + ": failed " + thisfile
      output = None
    if output != [[9, 7, 8], [7, 8, 6], [8, 6, 7], [6, 7, 5], [7, 5, 6], [5, 6, 4]]:
      print "----------------- Wrong: " + thisfile, output
    os.system("rm " + directory + thisfile + ".pyc")

if __name__ == "__main__":
  if len(sys.argv) == 4:
    check(sys.argv[1], sys.argv[2], sys.argv[3])
  if len(sys.argv) == 5 and sys.argv[2] == "anagrams":
    run_test_anagrams(sys.argv[1], sys.argv[2], sys.argv[3])
  elif len(sys.argv) == 5 and sys.argv[2] == "series":
    run_test_series(sys.argv[1], sys.argv[2], sys.argv[3])

