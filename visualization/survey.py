#!/usr/bin/env python

from style_chain import generate_chain, interpret_list_of_hints
import random
import urllib2
import urllib
data = {}

def main(flog_lower, flog_upper, maximum):
	selected = []
	while len(selected) < maximum:
		i = int(random.randint(1, 685)) #a <= N <= b
		cl = generate_chain(i, 1, 1.1, '../').head
		if cl.flog_score > flog_lower and cl.flog_score <= flog_upper and (i not in selected):
			try:
				pos_hints = interpret_list_of_hints(cl.get_positive_hint()[0], False).split("\n")[1:-1]
				neg_hints = interpret_list_of_hints(cl.get_negative_hint()[0], True).split("\n")[1:-1]
				if flog_upper > 8 and ((len(pos_hints) + len(neg_hints)) < 3):
					raise Exception
				data = {}
				data['code'] = cl.source_code
				data['lexers'] = 'Ruby'
				data['linenos'] = 'true'
				url_values = urllib.urlencode(data)
				url = 'http://hilite.me/api'
				to_post = url + '?' + url_values
				print urllib2.urlopen(to_post).read()
				print "(For our reference: " + str(cl.flog_score) + "  " + str(cl.index) + ")"
				print "\n"
				print "To improve your style, you might want to consider..."
				for item in pos_hints:
					print item
				for item in neg_hints:
					print item
				print "_________________________________"
				selected.append(i)
			except Exception:
				pass


if __name__ == '__main__':
    main(float('-inf'), 7, 2)
    print "============================="
    main(7, 20, 2)
    print "============================="
    main(20, float('inf'),2)