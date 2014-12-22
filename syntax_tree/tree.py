#!/usr/bin/python -tt

import sys
import re
import sexpdata # Install: pip install sexpdata
from zss import * # Install: pip install zss
import pdb

class MyNode(object):
    
    def __init__(self, label, depth):
        self.my_label = label
        self.my_depth = depth
        self.my_children = list()

    @staticmethod
    def get_children(node):
        return node.my_children

    @staticmethod
    def get_label(node):
        return str(node.my_depth) + "##" + str(node.my_label)

    @staticmethod
    def get_depth(node):
        return node.my_depth

    def addkid(self, node, before=False):
        if before: 
            self.my_children.insert(0, node)
        else: 
            self.my_children.append(node)
        return self

def main():
    #root1 = control_flow_tree("/Users/Beaver/CS169_Clustering/800/ast/33225_ast", "combine_anagrams")
    #root2 = weighted_syntax_tree("/Users/Beaver/CS169_Clustering/200/ast/1021_ast", "combine_anagrams")
    #root3 = abstract_syntax_tree("/Users/Beaver/CS169_Clustering/800/ast_with_helper/16827_ast", "combine_anagrams")
    #root4 = abstract_syntax_tree("/Users/Beaver/CS169_Clustering/800/ast_with_helper/13600_ast", "combine_anagrams")
    
    #printTree(root1)
    #print "------------"
    #printWST(root2)
    #print simple_distance(root1, root2)
    #printTree(root3)
    #print "------------"
    #printTree(root4)
    #print "------------"
    #print simple_distance(root3, root4)
    #root5 = control_flow_and_library_tree('799/ast/31177_ast', 'combine_anagrams', 'library_functions.np')
    #root5 = control_flow_tree('/home/joe/workspace/Education/cs169_clustering/799/ast/31177_ast', 'combine_anagrams')
    root5 = control_flow_and_library_tree('/home/joe/workspace/Education/cs169_clustering/799/ast/31177_ast', 'combine_anagrams')
    printTree(root5)

def build_sexp(filename, method=""):
    f = open(filename, 'r')
    try:
        sexp = sexpdata.loads(preprocess(f.read()))
    except Exception:
        print "LoadError (build_sexp in tree.py): " + filename
        return
    else:
#if method != "":
#sexp = extract_method(sexp, method)
        return sexp
    finally:
        f.close

def control_flow_tree(filename, method=""):
    return cft_from_sexp(build_sexp(filename, method))

def abstract_syntax_tree(filename, method=""):
    return ast_from_sexp(build_sexp(filename, method))

def weighted_syntax_tree(filename, method=""):
    return wst_from_sexp(build_sexp(filename, method))

def control_flow_and_library_tree(filename, method=""):
    return cft_and_library_from_sexp(build_sexp(filename, method))

def cft_from_sexp(sexp):
    root = Node("root")
    construct_cft_level(root, sexp)
    return root

def ast_from_sexp(sexp):
    root = Node("root")
    construct_ast_level(root, sexp)
    return root

def wst_from_sexp(sexp):
    root = MyNode("root", 0) 
    construct_wst_level(root, sexp)
    return root

def cft_and_library_from_sexp(sexp):
    root = Node('root')
    construct_cft_and_library_level(root, sexp)
    return root

def args_match(matchobj):
    array = matchobj.group("args").split(" ")
    return "(args " + ("argname " * (len(array)-1)) + "argname)"

def preprocess(ast_string):
    mydict = {
        's(': '(',
        'nil)': 'mynil)',
        'nil,': 'mynil',
        ':': '',
        ',': ''
    } 
    temp = multiple_replace(mydict, ast_string)
    temp1 = re.sub('\(lvar (.*?)\)', '(lvar lvarname)', temp) #lvar
    temp2 = re.sub('\(lasgn \w+ ', '(lasgn lasgnname ', temp1) #lasgn
    temp3 = re.sub('\(lasgn \w+\)', '(lasgn lasgnname)', temp2)
    temp4 = re.sub('\(args (?P<args>(\w+\s?)+)\)', args_match, temp3) #args
    temp5 = re.sub('\(lit \/.*?\/.*?\)', '(lit litname)', temp4) # escape regex
    temp6 = re.sub('\(lit (.*?)\)', '(lit litname)', temp5) 
    temp7 = re.sub('\(str ".*?"\)', '(str strname)', temp6) # e.g. (str "\n----\n") --> (str strname)
    return temp7 
    
def multiple_replace(dict, text):
    # Create a regular expression    from the dictionary keys
    regex = re.compile("(%s)" % "|".join(map(re.escape, dict.keys())))

    # For each match, look-up corresponding value in dictionary
    return regex.sub(lambda mo: dict[mo.string[mo.start():mo.end()]], text)     

def extract_method(sexp, method):
    # Check if should return the whole sexp
    if sexp[0].__class__.__name__ == "Symbol" and sexp[0]._val == "defn" and sexp[1]._val == method:
        return sexp
    # Do not detect recursively, only need to check first level
    for sub_sexp in sexp:
        if sub_sexp.__class__.__name__ == "list":        
            if sub_sexp[0]._val == "defn":
                if sub_sexp[1].__class__.__name__ == "str" and sub_sexp[1] == method:
                    return sub_sexp
                elif sub_sexp[1].__class__.__name__ == "Symbol" and sub_sexp[1]._val == method:
                    return sub_sexp

def construct_cft_level(parent, sub_sexp):
    classname = sub_sexp.__class__.__name__
    if not sub_sexp:
        return
    elif classname == "list":
        level_node = sub_sexp.pop(0)
        if level_node.__class__.__name__ == "Symbol":
            level_label = level_node._val
            if level_label in set(["until", "for", "while", "iter"]):
                new_node = Node("iter")
                parent.addkid(new_node)
                for e in sub_sexp:
                    construct_cft_level(new_node, e)    
            elif level_label == "if":
                new_node = Node("cond")
                parent.addkid(new_node)
                for e in sub_sexp:
                    construct_cft_level(new_node, e)
            else:
                for e in sub_sexp:
                    construct_cft_level(parent, e)
        else:
            return
    else:
        return

def construct_ast_level(parent, sub_sexp):
    classname = sub_sexp.__class__.__name__
    if not sub_sexp:
        return
    elif classname in set(["Bracket", "Symbol"]):
        parent.addkid(Node(sub_sexp._val))
        return
    elif classname in set(["str", "int", "bool", "float"]):
        parent.addkid(Node(sub_sexp))
        return
    else:
        level_boss = Node(sub_sexp.pop(0)._val)
        parent.addkid(level_boss)
        for e in sub_sexp:
            construct_ast_level(level_boss, e)

def construct_wst_level(parent, sub_sexp):
    classname = sub_sexp.__class__.__name__
    if not sub_sexp:
        return
    elif classname in set(["Bracket", "Symbol"]):
        parent.addkid(MyNode(sub_sexp._val, MyNode.get_depth(parent) + 1))
        return
    elif classname in set(["str", "int", "bool", "float"]):
        parent.addkid(MyNode(sub_sexp, MyNode.get_depth(parent) + 1))
        return
    else: 
        level_boss = MyNode(sub_sexp.pop(0)._val, MyNode.get_depth(parent) + 1)
        parent.addkid(level_boss)
        for e in sub_sexp:
            construct_wst_level(level_boss, e)
            
def construct_cft_and_library_level(parent, sub_sexp):
    classname = sub_sexp.__class__.__name__
    if not sub_sexp:
        return
    elif classname == "list":
        level_node = sub_sexp.pop(0)
        if level_node.value() == 'call':
            library_call = sub_sexp[1].value()
            if library_call == []:
                library_call = '[]'

        if level_node.__class__.__name__ == "Symbol":
            level_label = level_node._val
            
            if level_label in set(["until", "for", "while", "iter"]):
                new_node = Node("iter")
                parent.addkid(new_node)
                for e in sub_sexp:
                    construct_cft_and_library_level(new_node, e)    
            elif level_label == "if":
                new_node = Node("cond")
                parent.addkid(new_node)
                for e in sub_sexp:
                    construct_cft_and_library_level(new_node, e)
            elif level_label == "call":
                new_node = Node(library_call)
                parent.addkid(new_node)
                for e in sub_sexp:
                    construct_cft_and_library_level(new_node, e)
            else:
                for e in sub_sexp:
                    construct_cft_and_library_level(parent, e)
        else:
            return
    else:
        return

def printLinearTree(node):
    stack = []
    stack.append(node)
    while stack:
        current = stack.pop()
        print Node.get_label(current)
        stack += Node.get_children(current)[::-1]
        
def printTree(node):
    def printHelper(node, indentation):
        for child in Node.get_children(node)[len(Node.get_children(node))/2:]:
            printHelper(child, indentation + '    ')
        print indentation + Node.get_label(node)
        for child in Node.get_children(node)[:len(Node.get_children(node))/2]:
            printHelper(child, indentation + '    ')
    printHelper(node, '')

def printWST(MyNode):
    stack = []
    stack.append(MyNode)
    while stack:
        current = stack.pop()
        print MyNode.get_label(current)
        stack += MyNode.get_children(current)[::-1]

if __name__ == '__main__' :
    main()
