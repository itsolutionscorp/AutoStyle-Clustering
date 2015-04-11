def hey(str_in):
    #If the string is all uppercase, it's a shout.
    if str_in.isupper(): 
            return 'Whoa, chill out!'
    
    #If the string ends with '?' (and fails the
    #shout test, then it's a question.
    elif str_in.endswith('?'):
        return 'Sure.'
    
    #Space or empty? Nothing is being said.
    elif not str_in or str_in.isspace():
        return 'Fine. Be that way!'
    
    #All other cases...
    else:
        return 'Whatever.'
