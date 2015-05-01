from app import app
from flask import render_template, request
import os
import sys
import ast
import numpy as np
import subprocess
import glob
sys.path.insert(0, os.path.abspath('../visualization/'))
sys.path.insert(0, os.path.abspath('../featurization/'))
sys.path.insert(0, os.path.abspath('../scripts_python/'))
from style_chain import generate_chain, interpret_list_of_hints
from individual_features import generate_individual_features
# from python_newsubmission_edit_dist import compute_edit_distances
start_index = None
max_hints = 3
flog = 1
feedback = None
chain = None
posts = None
hints = None


@app.route('/')
@app.route('/index')
def index():
    posts = []
    directories = [item.lstrip('../assignments/')for item in glob.glob('../assignments/*/*')]
    return render_template("index.html", ast_dist=3, flog_diff=0, title='Home', posts=posts, directories=directories)


@app.route('/submit_form/', methods=['POST'])
def submit_form():
    global hints
    global chain

    data_loc = request.form['directory']
    start_index = int(request.form['start'])
    max_hints = int(request.form['ast_slider'])
    flog = float(request.form['flog_slider'])
    feedback = request.form['feedback']
    

    # print "----------- submitted --------------"
    # print "directory", data_loc
    # print "start", start_index
    # print "max_hints", max_hints
    # print "flog", flog
    # print "feedback", feedback


    feedback = feedback.split(" ")
    try:
      feedback.remove("")
    except:
      pass

    final_feedback = []
    if hints is not None:
      for i, item in enumerate(feedback):
        item = item.split(",")
        print item
        final_feedback.append((hints[i], int(item[1]), int(item[3]), item[0]))
    
    # feedback format: (('non-sentence name of hint', index/position in chain, bad_hint or not, positive or negative) , (), ()  )
    chain = generate_chain(start_index, max_hints, flog, os.path.abspath('../') + "/", data_dir = "assignments/" + data_loc,feedback=final_feedback, old_chain=chain, language=data_loc.split("/")[0])
    posts = [] 
    hints = []
    cl = chain.head
    while cl:
      print cl.index, "(flog ",cl.flog_score, ")   -->   ", 
      if cl.next:
        pos_hints = cl.get_positive_hint()
        neg_hints = cl.get_negative_hint()
        for ph in pos_hints[0]:
          hints.append(ph) 
        for nh in neg_hints[0]:
          hints.append(nh)
        posts.append({'code': cl.source_code, 'positive_hint': interpret_list_of_hints(pos_hints[0], False).split("\n")[1:-1], 'positive_lines': pos_hints[1], 'negative_lines': neg_hints[1], 'negative_hint': interpret_list_of_hints(neg_hints[0], True).split("\n")[1:-1], 'positive_hint_locations': pos_hints[2], 'negative_hint_locations': neg_hints[2]})
      else:
        posts.append({'code': cl.source_code, 'positive_hint': "", 'negative_hint': ''})
      cl = cl.next
      directories = [item.lstrip('../assignments/') for item in glob.glob('../assignments/*/*')]
      lang = data_loc.split("/")[0]
      if lang == "java":
        lang = "text/x-java"
    return render_template("index.html",
                           ast_dist=max_hints,
                           flog_diff=flog,
                           title='Home',
                           home="../",
                           posts=posts, start=start_index, directory=data_loc, directories=directories, language=lang)

@app.route('/')
@app.route('/improve/')
def improve():    
    post = []
    directories = [item.lstrip('../assignments/')for item in glob.glob('../assignments/*/*')]
    return render_template("improve.html",  post=post, directories=directories)

@app.route('/submit_improveform/', methods=['POST'])
def improve_submit():
    # import time
    # time.sleep(10)
    data_loc = request.form['directory']
    start_index = request.form['start']
    print start_index
    post = []
    home_dir = os.path.abspath('../') + "/"
    data_dir = home_dir+"assignments/" + data_loc
    lang = data_loc.split("/")[0]
    style_score = "flog" if lang == "ruby" else "abc"
    cl = None
    if start_index == "":
      newcode = request.form['new_submission']
      start_index = len(glob.glob(data_dir+"/src/*.py"))
      print "newstartindex", start_index
      try:
        ast_node = ast.parse(newcode, mode='exec')
        try:
          os.remove(data_dir+"/ast_dump/.DS_Store")
        except:
          pass
        with open(data_dir+"/ast_dump/" + str(start_index)+".ast", "w") as f:
          f.write(ast.dump(ast_node, annotate_fields=False).replace(")","}").replace("(","{").replace(":",""))
        proc = subprocess.Popen(["java -jar "+ home_dir+"syntax_tree/TreeEditDistance.jar " + data_dir], stdout=subprocess.PIPE, shell=True)
        (ed, err) = proc.communicate()
        ed = ed.rstrip(",").split(',')
        with open( data_dir+"/src/" + str(start_index)+ ".py", "w") as f:
          f.write(newcode)
        function_name = newcode.split("\n")[0].split("(")[0].replace("def ", "").rstrip(" ")
        feature_vector, feature_lines = generate_individual_features(lang, function_name, start_index, ["libcall", "control_flow", "recursion", "duplicate_treegram"], data_dir, class_name=None)
        with open(data_dir+"/feature/style_features.np",'a') as f_handle:
          np.savetxt(f_handle,feature_vector.T)
        with open(data_dir+"/feature/feature_line_nums.np",'a') as f_handle:
          np.savetxt(f_handle,feature_lines.T)
        print feature_vector.T
        print feature_lines.T
        ss, sse = generate_individual_features(lang, function_name, start_index, [style_score], data_dir, class_name=None)
        with open(data_dir+"/feature/style_scores.np",'a') as f_handle:
          np.savetxt(f_handle,ss.T)
        row = np.array(ed, ndmin=2).astype(np.float)
        ed.append(0)
        col = np.array(ed, ndmin=2).astype(np.float)
        dist_matrix = np.loadtxt(data_dir+"/gen/ast_dist_matrix.np")
        dist_matrix_new = np.vstack([dist_matrix,row])
        dist_matrix_new = np.hstack([dist_matrix_new, col.T])
        np.savetxt(data_dir+"/gen/ast_dist_matrix.np", dist_matrix_new)
        cl = generate_chain(int(start_index), 4, 0.14, home_dir, data_dir = "assignments/" + data_loc, language=data_loc.split("/")[0]).head
      finally:
        try:
          np.savetxt(data_dir+"/gen/ast_dist_matrix.np", dist_matrix)
        except:
          pass
        try:  
          ss = np.loadtxt(data_dir+"/feature/style_scores.np")
          ss = np.delete(ss, ss.shape[0]-1,0)
          np.savetxt(data_dir+"/feature/style_scores.np", ss)
        except:
          pass
        try:
          fln = np.loadtxt(data_dir+"/feature/feature_line_nums.np")
          fln = np.delete(fln, fln.shape[0]-1,0)
          np.savetxt(data_dir+"/feature/feature_line_nums.np", fln)
        except:
          pass
        try:
          sf = np.loadtxt(data_dir+"/feature/style_features.np")
          sf = np.delete(sf, sf.shape[0]-1,0)
          np.savetxt(data_dir+"/feature/style_features.np", sf)
        except:
          pass
        try:
          os.remove(data_dir+"/ast_dump/"+str(start_index)+".ast")
        except:
          pass
        try:
          os.remove(data_dir+"/src/"+str(start_index)+".py")
        except:
          pass
    else:
      print "s-------", int(start_index)
      cl = generate_chain(int(start_index), 4, 0.14, home_dir, data_dir = "assignments/" + data_loc, language=data_loc.split("/")[0]).head
    hints = []
    pos_hints = cl.get_positive_hint()
    neg_hints = cl.get_negative_hint()
    print pos_hints
    print neg_hints
    for ph in pos_hints[0]:
      hints.append(ph) 
    for nh in neg_hints[0]:
      hints.append(nh)
    post = {'code': cl.source_code, 'positive_hint': interpret_list_of_hints(pos_hints[0], False).split("\n")[1:-1], 'positive_lines': pos_hints[1], 'negative_lines': neg_hints[1], 'negative_hint': interpret_list_of_hints(neg_hints[0], True).split("\n")[1:-1], 'positive_hint_locations': pos_hints[2], 'negative_hint_locations': neg_hints[2]}
    if lang == "java":
        lang = "text/x-java"
    directories = [item.lstrip('../assignments/')for item in glob.glob('../assignments/*/*')]
    return render_template("improve.html",  post=post, directory=data_loc, directories=directories,language=lang)
