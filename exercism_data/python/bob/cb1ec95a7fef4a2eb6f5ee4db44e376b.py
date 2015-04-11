# -*- coding: utf-8 -*-

'''Bob is a lackadaisical teenager. In conversation, his responses are very limited. Bob answers 'Sure.' if you ask him a question. He answers 'Whoa, chill out!' if you yell at him. He says 'Fine. Be that way!' if you address him without actually saying anything. He answers 'Whatever.' to anything else.
Call the hey function and supply a string variable to return Bob's classic oneliners.'''

def yelling(words):
	return words.isupper()
def silence(words):
	return not words.strip()
def question(words):
	return words.endswith('?')    

def hey(words):
    if yelling(words):
	    return 'Whoa, chill out!'
    elif silence(words):
	    return 'Fine. Be that way!'
    elif question(words):
	    return 'Sure.'
    else:
	    return 'Whatever.'     
