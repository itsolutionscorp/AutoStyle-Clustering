'''etl.py
	created 20 Oct 2014
	by @jestuber '''

def transform(old):
	return {
		string.lower(): score
		for score, str_list in old.iteritems()
		for string in str_list
	}
