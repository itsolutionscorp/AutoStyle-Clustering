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
        start_index = str(len(glob.glob(data_dir+"/src/*.py")))
        try:
            # create AST and write it to file
            try:
                if newcode == "":
                    raise Exception
                dump_single_ast(data_dir+"/ast/" + start_index +".ast", newcode)
            except:
                oldpost['code'] = newcode
                return render_template("improve_style.html",  post= oldpost, directories=directories, error="Your code has syntax errors. Please fix them and resubmit.\n", language=lang)
            
            # write source code to file
            with open( data_dir+"/src/" + start_index + ".py", "w") as f:
                f.write(newcode)

            # test basic code correctness
            function_name = newcode.split("\n")[0].split("(")[0].replace("def ", "").rstrip(" ")
            if data_loc == "python/61a_hw4" or data_loc == u"python/61a_hw4":
                res, codeoutput = test_correctness_61a_hw4(data_dir + "/src/", start_index + ".py")
                if not res:
                    oldpost['code'] = newcode
                    return render_template("improve_style.html",  post= oldpost, directories=directories, error="Your code does not return the right result. Please fix it and resubmit. " + codeoutput, language = lang)
            
            # process edit distance
            proc = subprocess.Popen(["java -jar "+ home_dir+"syntax_tree/TreeEditDistance.jar " + data_dir + " " + "python"], stdout=subprocess.PIPE, shell=True)
            (ed, err) = proc.communicate()
            ed = ed.rstrip(",").split(',')
            row = np.array(ed, ndmin=2).astype(np.float)
            ed.append(0)
            col = np.array(ed, ndmin=2).astype(np.float)
            dist_matrix = np.loadtxt(data_dir+"/gen/java_ast_dist_matrix.np")
            dist_matrix = np.vstack([dist_matrix,row])
            dist_matrix = np.hstack([dist_matrix, col.T])

            # process features
            feature_vector, feature_lines = generate_individual_features("python", function_name, int(start_index), ["libcall", "control_flow", "recursion", "duplicate_treegram"], data_dir, class_name=None)
            style_features = np.loadtxt(data_dir + '/feature/style_features.np', ndmin=2)
            style_features = np.vstack([style_features, feature_vector.T])
            line_num_matrix = np.loadtxt(data_dir + '/feature/feature_line_nums.np', ndmin=2)
            line_num_matrix = np.vstack([line_num_matrix, feature_lines.T])

            # process flog/abc
            ss, sse = generate_individual_features("python", function_name, int(start_index), ["abc"], data_dir, class_name=None)
            style_scores = np.loadtxt(data_dir + '/feature/style_scores.np', ndmin=2)
            style_scores = np.insert(style_scores, style_scores.shape[0], ss, axis = 0)
            try:
                cl = generate_chain_loaded(start_index = int(start_index), max_hints = 4, style_score_weight = 0.14, home_dir = home_dir, data_dir = "intervention/data/" + data_loc, language="python", style_scores = style_scores, style_features = style_features, line_num_matrix = line_num_matrix, distances = dist_matrix).head
            except Exception as e:
                print e
        except Exception as e:
            print e
        finally:
            try:
                os.remove(data_dir+"/ast/"+start_index+".ast")
            except OSError:
                pass
            except Exception as e:
                print "Failed to remove generated ast file", e
            try:
                os.remove(data_dir+"/src/"+start_index+".py")
            except OSError:
                pass
            except Exception as e:
                print "Failed to remove generated src file", e
    elif lang == "ruby" or lang == u"ruby":
        cl = intervene_ruby(newcode, home_dir, data_dir)
    else:
        raise Exception

    hints = []
    try:
        pos_hints = cl.get_positive_hint()
        neg_hints = cl.get_negative_hint()
        for ph in pos_hints[0]:
            hints.append(ph)
        for nh in neg_hints[0]:
            hints.append(nh)
    except:
        error = "Sorry, unable to process your solution. Please report this to us."
        oldpost['code'] = newcode
        return render_template("improve_style.html",  post= oldpost, directories=directories, error=error, language = lang)

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
