# -*- coding: utf-8 -*-

'''Bob is a lackadaisical teenager. In conversation, his responses are very limited. Bob answers 'Sure.' if you ask him a question. He answers 'Whoa, chill out!' if you yell at him. He says 'Fine. Be that way!' if you address him without actually saying anything. He answers 'Whatever.' to anything else.
Call the hey function and supply a string variable to return Bob's classic oneliners.'''

def all_caps(words):
	#Check to see if the string variable is ALL CAPS
	return words.isupper()
def empty_string(words):
	#Check to see if string variable is empty.
	return not words.strip()
def question_mark(words):
	#Check to see if the string variable ends with a ?
	return words.endswith('?')    

def hey(words):
    if all_caps(words):
	    return 'Whoa, chill out!'
    elif empty_string(words):
	    return 'Fine. Be that way!'
    elif question_mark(words):
	    return 'Sure.'
    else:
	    return 'Whatever.'     
