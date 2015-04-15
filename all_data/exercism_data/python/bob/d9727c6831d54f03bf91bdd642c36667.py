# -*- coding: utf-8 -*-

'''Bob is a lackadaisical teenager. In conversation, his responses are very limited. Bob answers 'Sure.' if you ask him a question. He answers 'Whoa, chill out!' if you yell at him. He says 'Fine. Be that way!' if you address him without actually saying anything. He answers 'Whatever.' to anything else.
Call the hey function and supply a string variable to return Bob's classic oneliners.'''

def hey(words):
    #Drop white spaces from string variable
    words = words.strip()
    #Check to see if the string variable is ALL CAPS
    if words.isupper():
        return 'Whoa, chill out!'
    #Check to see if string variable is empty. 
    elif len(words) == 0:
        return 'Fine. Be that way!'
    #Check to see if the string variable ends with a ?
    elif words[-1] == '?':
        return 'Sure.'
    #Failsafe
    else:
        return 'Whatever.'
      
