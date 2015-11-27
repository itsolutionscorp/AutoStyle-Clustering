#!/usr/bin/env python
import sys
import os
import glob
import re
import ast
import betterast
import pandas as pd
sys.path.insert(0, os.path.abspath('../featurization'))
sys.path.insert(0, os.path.abspath('featurization'))
from individual_features import abc_score

control = {}
auto = {}

def get_node_label(node):
    '''
    Get the label of a python ast object.
    '''
    return str(type(node)).replace("<class '_ast.","").replace("'>","")

def build_tree(node, ast_node, functions):
    '''
    Recursively construct a Node object out of a python ast object.
    '''
    if isinstance(ast_node, str) or isinstance(ast_node, int) or (ast_node == None):
        return node
    n = [i for i in ast.iter_child_nodes(ast_node)]
    for i,kid in enumerate(n):
        label = get_node_label(kid)
        if 'lineno' in kid._attributes:
            line = kid.lineno
        elif 'lineno' in ast_node._attributes:
            line = ast_node.lineno
        else:
            line = 0
        if label == "FunctionDef":
            name = [r[1] for r in ast.iter_fields(kid) if r[0] == 'name'][0]
            label = "FunctionDef: " + name
        if i==0 and node.label[0:4] == "Call" and 'func' in dir(ast_node):
            label = [r[1] for r in ast.iter_fields(kid) if r[0] == 'attr' or r[0] == 'id'][0]
        if label in functions:
            label = functions[label]
        kidNode = betterast.Node(label + "::" + str(line))
        node.addkid(kidNode)
        build_tree(kidNode, kid, functions)
    return node

def generate_python_ast(code):
    '''
    Generate an Node object representing an ast for a python file.
    '''
    try:
        ast_node = ast.parse(code, mode='exec')
    except Exception as e:
        print "FAILED: to generate ast: " + str(e)
        return
    ast_node = ast.fix_missing_locations(ast_node)
    if 'lineno' in ast_node._attributes:
        b_node = betterast.Node(get_node_label(ast_node) + "::" + ast_node.lineno)
    else:
        b_node = betterast.Node(get_node_label(ast_node))
    tree = build_tree(b_node,ast_node,{})
    return tree

def get_score(code): 
    ast = generate_python_ast(code)
    num_lines = len(code.split("\n"))
    return abc_score(ast, num_lines)
    
def clean_code(string):
    string = re.sub(re.compile("\r\n"), "\n", string)
    string = re.sub(re.compile("\n\n"), "\n", string)
    string = re.sub(re.compile("#.*?\n") ,"\n" ,string) # remove all occurance singleline comments (//COMMENT\n ) from string
    string = re.sub(re.compile("[\n\t ]*#.*?$") ,"" ,string) # remove all occurance singleline comments (//COMMENT\n ) from string
    string = re.sub(re.compile("\n[\t ]*\n"), "\n", string) #remove occurances of empty lines
    string = re.sub(re.compile("\n[\t ]*\n"), "\n", string)
    return string.rstrip("\n\t ")

def process_results(home_dir):
    files = glob.glob(home_dir + "/intervention/results/*")
    for f in files:
        print f
        if f.split("/")[-1].startswith("control"):
            contents = open(f).read()
            code = clean_code(contents)
            score = get_score(code)[0][0]
            spl = f.split("_")
            if len(spl) == 5:
                student, timestamp, timetaken, flog = f.split("_")[1:]
            else:
                print "control split failure ", f
            control[f.split("/")[-1]] = [student, timestamp, timetaken, score, code]
        else:
            contents = open(f).read()
            code, pos = contents.split('Positive Hints\n')
            pos, neg = pos.split('Negative Hints\n')
            neg, approach = neg.split('Approach Hints\n')
            approach, skeleton = approach.split("Skeleton\n")
            approach = approach.strip("\n")
            skeleton = skeleton.strip("\n")
            pos = pos.strip("\n")
            neg = neg.strip("\n")
            code = clean_code(code)
            score = get_score(code)[0][0]
            spl = f.split("_")
            if len(spl) == 5:
                student, timestamp, timetaken, cluster, flog = spl
            else:
                print "auto split four",f 
                student, timestamp, timetaken, cluster = spl
            auto[f.split("/")[-1]] = [student, timestamp, timetaken, score, code, pos, neg, approach, skeleton, cluster]
    control_df = pd.DataFrame.from_dict(data = control, orient="index")
    control_df.columns = ["student_id", "timestamp", "time_taken", "flog_score", "code"]
    auto_df = pd.DataFrame.from_dict(data = auto,orient="index")
    auto_df.columns = ["student_id", "timestamp", "time_taken", "flog_score", "code", "pos_hints", "neg_hint", "approach_hints", "skeleton", "cluster"]
    control_df.to_csv("control_df.csv", sep="~")
    auto_df.to_csv("auto_df.csv", sep="~")

if __name__ == '__main__': 
    if len(sys.argv) != 2:
        print "Usage: process_intervention_results.py <home_dir>"
        exit(0)
    process_results(sys.argv[1])