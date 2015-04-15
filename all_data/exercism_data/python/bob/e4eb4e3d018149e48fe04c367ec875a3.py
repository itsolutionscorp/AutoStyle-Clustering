import re

def hey(what):
    what = what.strip()
    if is_silent(what):
    	return "Fine. Be that way!"
    elif is_shout(what):
    	return "Whoa, chill out!"
    elif is_question(what):
    	return "Sure." 
    else:
    	return "Whatever."

def is_shout(what):	
	return is_uppercase(what) and has_letters(what)

def is_question(what): 
	return what[-1] == "?"

def is_silent(what): 
	return what == ''

def is_uppercase(word): 
	return word == word.upper()

def has_letters(word): 
	exp = re.compile('[A-Za-z]')
	return exp.search(word) is not None
