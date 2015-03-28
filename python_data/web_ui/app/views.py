from app import app
from flask import render_template, request, url_for
import os
import sys
sys.path.insert(0, os.path.abspath('../../visualization/'))
from style_chain import generate_chain, interpret_list_of_hints
start_index = None
ast = 0.05
flog = 1
feedback = None
chain = None
posts = None
hints = None

@app.route('/')
@app.route('/index')
def index():
    posts = []
    return render_template("index.html", ast_dist=0.05, flog_diff=0, title='Home', posts=posts, start=1)

@app.route('/submit_form/', methods=['POST'])
def submit_form():
    global hints
    global chain
    start_index = int(request.form['start'])
    ast = float(request.form['ast_slider'])
    print request.form['flog_slider']
    flog = float(request.form['flog_slider'])
    feedback = request.form['feedback']
    feedback = feedback.split(" ")
    try:
      feedback.remove("")
    except:
      pass
    final_feedback = []
    if hints != None:
      for i, item in enumerate(feedback):
        item = item.split(",")
        final_feedback.append((hints[i], int(item[1]), int(item[3]), item[0]))
    # feedback format: (('non-sentence name of hint', index/position in chain, bad_hint or not, positive or negative) , (), ()  )
    chain = generate_chain(start_index, ast, flog, os.path.abspath('../../') + "/", feedback=final_feedback, old_chain=chain)
    posts = []
    hints = []
    cl = chain.head
    while cl:
      print cl.index, cl.flog_score, "--> "
      if cl.next:
        pos_hints = cl.get_positive_hint()
        neg_hints = cl.get_negative_hint()
        for ph in pos_hints[0]:
          hints.append(ph)
        for nh in neg_hints[0]:
          hints.append(nh)
        posts.append({'code': cl.source_code, 'positive_hint': interpret_list_of_hints(pos_hints[0], False).split("\n")[1:-1], 'positive_lines': pos_hints[1], 'negative_lines': neg_hints[1], 'negative_hint':interpret_list_of_hints(neg_hints[0], True).split("\n")[1:-1]})
      else:
        posts.append({'code': cl.source_code, 'positive_hint': "", 'negative_hint': ''})
      cl = cl.next

    return render_template("index.html",
                            ast_dist=ast,
                            flog_diff=flog,
                           title='Home',
                           home="../",
                           posts=posts, start=start_index)
