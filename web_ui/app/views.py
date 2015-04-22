from app import app
from flask import render_template, request
import os
import sys
from glob import glob
sys.path.insert(0, os.path.abspath('../visualization/'))
from style_chain import generate_chain, interpret_list_of_hints
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
    directories = [item.lstrip('../assignments/')for item in glob('../assignments/*/*')]
    # return render_template("carousel.html")
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
      directories = [item.lstrip('../assignments/') for item in glob('../assignments/*/*')]
      lang = data_loc.split("/")[0]
      if lang == "java":
        lang = "text/x-java"
    return render_template("index.html",
                           ast_dist=max_hints,
                           flog_diff=flog,
                           title='Home',
                           home="../",
                           posts=posts, start=start_index, directory=data_loc, directories=directories, language=lang)
