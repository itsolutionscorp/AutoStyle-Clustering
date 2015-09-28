from app import app
from flask import render_template, request, send_file
import os
import re
import sys
import numpy as np
import subprocess
import glob
sys.path.insert(1, os.path.abspath('../featurization/'))
sys.path.insert(1, os.path.abspath('../scripts_python/'))
from style_chain import generate_chain, interpret_list_of_hints
from individual_features import generate_individual_features
from python_ast_dump import dump_single_ast
oldpost = {}

def intervene_python(newcode, home_dir, data_dir):
    pass

def intervene_ruby():
    raise Exception


@app.route('/')
@app.route('/improve/')
def improve():
    global oldpost
    post = {}
    oldpost = post
    directories = [item.lstrip('../intervention/data/')for item in glob.glob('../intervention/data/*/*')]
    return render_template("improve_style.html",  post=post, directories=directories)

def repl_matches(matchobj):
  return "".join(["$" for i in range(len(matchobj.group(0)))])

@app.route('/submit_improveform/', methods=['POST'])
def improve_submit():
    global oldpost
    data_loc = request.form['directory']
    newcode = request.form['new_submission']
    home_dir = os.path.abspath('../') + "/"
    data_dir = home_dir+"intervention/data/" + data_loc
    directories = [item.lstrip('../intervention/data/')for item in glob.glob('../intervention/data/*/*')]
    lang = data_loc.split("/")[0]
    with open( data_dir+"/save/" + str(len(glob.glob(data_dir+"/save/*.py"))) + ".py", "w") as f:
        f.write(newcode)
    cl = None
    if lang == "python" or lang == u"python":
        # cl = intervene_python(newcode, home_dir, data_dir)
        start_index = len(glob.glob(data_dir+"/src/*.py"))
        try:
            # create AST and write it to file
            try:
                if newcode == "":
                    raise Exception
                dump_single_ast(data_dir+"/ast/" + str(start_index)+".ast", newcode)
            except:
                oldpost['code'] = newcode
                return render_template("improve_style.html",  post= oldpost, directories=directories, error="Your code has syntax errors. Please fix them and resubmit.")
            # add code correctness test here

            # process edit distance
            proc = subprocess.Popen(["java -jar "+ home_dir+"syntax_tree/TreeEditDistance.jar " + data_dir + " " + "python"], stdout=subprocess.PIPE, shell=True)
            (ed, err) = proc.communicate()
            ed = ed.rstrip(",").split(',')

            # write source code to file
            with open( data_dir+"/src/" + str(start_index) + ".py", "w") as f:
                f.write(newcode)
            function_name = newcode.split("\n")[0].split("(")[0].replace("def ", "").rstrip(" ")
            feature_vector, feature_lines = generate_individual_features("python", function_name, start_index, ["libcall", "control_flow", "recursion", "duplicate_treegram"], data_dir, class_name=None)
            with open(data_dir+"/feature/style_features.np",'a') as f_handle:
                np.savetxt(f_handle,feature_vector.T)
            with open(data_dir+"/feature/feature_line_nums.np",'a') as f_handle:
                np.savetxt(f_handle,feature_lines.T)
            ss, sse = generate_individual_features("python", function_name, start_index, ["abc"], data_dir, class_name=None)
            with open(data_dir+"/feature/style_scores.np",'a') as f_handle:
                np.savetxt(f_handle,ss.T)
            try:
                print "-----"
                row = np.array(ed, ndmin=2).astype(np.float)
                print "bbb"
                ed.append(0)
                col = np.array(ed, ndmin=2).astype(np.float)
                print "ccc"
                dist_matrix = np.loadtxt(data_dir+"/gen/java_ast_dist_matrix.np")
                print "ddd"
                dist_matrix_new = np.vstack([dist_matrix,row])
                print "eee"
                dist_matrix_new = np.hstack([dist_matrix_new, col.T])
                print "fff"
                np.savetxt(data_dir+"/gen/java_ast_dist_matrix.np", dist_matrix_new)
                print "ggg"
                cl = generate_chain(int(start_index), 4, 0.14, home_dir, data_dir = "intervention/data/"+ data_loc, language="python").head
            except Exception as e:
                print e
            finally:
                try:
                    np.savetxt(data_dir+"/gen/java_ast_dist_matrix.np", dist_matrix)
                except Exception as e:
                    print "Failed to restore dist_matrix to original state", e
                try:
                    ss = np.loadtxt(data_dir+"/feature/style_scores.np")
                    ss = np.delete(ss, ss.shape[0]-1,0)
                    np.savetxt(data_dir+"/feature/style_scores.np", ss)
                except Exception as e:
                    print "Failed to restore style_scores to original state", e
                try:
                    fln = np.loadtxt(data_dir+"/feature/feature_line_nums.np")
                    fln = np.delete(fln, fln.shape[0]-1,0)
                    np.savetxt(data_dir+"/feature/feature_line_nums.np", fln)
                except Exception as e:
                    print "Failed to restore feature_line_nums to original state", e
                try:
                    sf = np.loadtxt(data_dir+"/feature/style_features.np")
                    sf = np.delete(sf, sf.shape[0]-1,0)
                    np.savetxt(data_dir+"/feature/style_features.np", sf)
                except Exception as e:
                    print "Failed to restore style_features to original state", e
        finally:
            try:
                os.remove(data_dir+"/ast/"+str(start_index)+".ast")
            except:
                print "Failed to remove generated ast file"
            try:
                os.remove(data_dir+"/src/"+str(start_index)+".py")
            except:
                print "Failed to remove generated src file"
    elif lang == "ruby" or lang == u"ruby":
        cl = intervene_ruby(newcode, home_dir, data_dir)
    else:
        raise Exception

    hints = []
    pos_hints = cl.get_positive_hint()
    neg_hints = cl.get_negative_hint()
    for ph in pos_hints[0]:
        hints.append(ph)
    for nh in neg_hints[0]:
        hints.append(nh)
    skeleton = "Sorry, no additional help available."
    if cl.next:
        pattern = "def |for |[\ \t]if|print|return |#|//|\"|\/\*|while|until|=| in |,|elsif|elif|else if|:|unless|else| end |\+|\-|\*|\^|[_a-zA-Z0-9]*\(|\t| |\n|\r\n|\[|\]|\{\}|\)|\("
        cd = re.sub(pattern,repl_matches, cl.next.source_code)
        if len(cl.next.source_code) != len(cd):
            skeleton = "".join(re.findall(pattern, cl.next.source_code, flags=re.M))
        else:
            skeleton = ""
            for i,character in enumerate(cl.next.source_code):
                if cd[i] == "$":
                    skeleton += character
                else:
                    skeleton += "-"
        skeleton = re.sub("#.*\n", "",skeleton)
    post = {'code': cl.source_code, 'positive_hint': interpret_list_of_hints(pos_hints[0], False).split("\n")[1:-1], 'positive_lines': pos_hints[1], 'negative_lines': neg_hints[1], 'negative_hint': interpret_list_of_hints(neg_hints[0], True).split("\n")[1:-1], 'positive_hint_locations': pos_hints[2], 'negative_hint_locations': neg_hints[2], 'skeleton':skeleton}
    oldpost = post
    if lang == "java":
        lang = "text/x-java"
    return render_template("improve_style.html",  post=post, directory=data_loc, directories=directories,language=lang)
