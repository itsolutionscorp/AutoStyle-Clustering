#requires python regex module to support unicode
import regex

def hey(what):
	patterns = [u'[^\p{Lowercase}]+\p{Uppercase}{1,}(\?|\!|$)', u'.+\?$', u'^[\s\t]{0,}$']
	responses = ['Whoa, chill out!', 'Sure.', 'Fine. Be that way!']

	for pattern, response in zip(patterns, responses):
		if regex.match(pattern, what):
			return response

	return 'Whatever.'
