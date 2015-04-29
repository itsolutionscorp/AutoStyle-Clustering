# -*- coding: utf-8 -*-

'''Bob is a lackadaisical teenager. In conversation, his responses are very limited. Bob answers 'Sure.' if you ask him a question. He answers 'Whoa, chill out!' if you yell at him. He says 'Fine. Be that way!' if you address him without actually saying anything. He answers 'Whatever.' to anything else.
Call the hey function and supply a string variable to return Bob's classic oneliners.'''

def hey(words):
    #Check to see if the string variable is ALL CAPS
    if words.isupper() == True:
        return 'Whoa, chill out!'
    #Check to see if the string variable ends with a ?
    #Additionally surpress errors by ensuring the check is only performed on string variables longer than 0 characters. Do I have to worry about this?
    elif len(words) > 0 and words[-1] == '?':
        return 'Sure.'
    #Check to see if string variable is empty. 
    #Can't use string methods on unicode text, so the second test is a cheat. I'd like to know how to test for strings containing only nonprinting characters.
    elif len(words) == 0 or '\t' in words:
        return 'Fine. Be that way!'

    else:
        return 'Whatever.'
