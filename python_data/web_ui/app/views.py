from app import app
from flask import render_template,request, url_for
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

@app.route('/')
@app.route('/index')
def index():
    posts = []
    return render_template("index.html",ast_dist=0.05,flog_diff=1,title='Home',posts=posts, start=1)

@app.route('/submit_form/', methods=['POST'])
def submit_form():
    start_index=int(request.form['start'])
    ast=float(request.form['ast_slider'])
    print request.form['flog_slider']
    flog=float(request.form['flog_slider'])

    feedback = request.form['feedback']
    # print feedback
    chain = generate_chain(start_index,ast,flog,os.path.abspath('../../')+"/")
    posts = []
    cl = chain.head
    while cl:
      if cl.next:
        pos_hints= cl.get_positive_hint()
        neg_hints = cl.get_negative_hint()
        posts.append({'code': cl.source_code, 'positive_hint': interpret_list_of_hints(pos_hints[0], False).split("\n")[1:-1], 'positive_lines': pos_hints[1],'negative_lines': neg_hints[1], 'negative_hint':interpret_list_of_hints(neg_hints[0], True).split("\n")[1:-1]})
      else:
        posts.append({'code': cl.source_code, 'positive_hint': "", 'negative_hint': ''})
      cl = cl.next
    print posts
    print "------",feedback
    return render_template("index.html",
                            ast_dist=ast,
                            flog_diff=flog,
                           title='Home',
                           home="../",
                           posts=posts, start = start_index)
