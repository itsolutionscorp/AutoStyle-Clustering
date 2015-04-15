# Bob python exercism, 2nd iteration

def is_question(s):
    """ Checks if last character is a question mark """
    return s[len(s)-1] == '?'
        
def contains_alpha(s):
    """ Check if a string contains letters """
    return any(c in "abcdefghijklmnopqrstuvwxyz" for c in s.lower())

    
def is_yelling(s):
    """ Checks if statement contains letters and if they are all caps """
    return s.upper() == s and contains_alpha(s)

        
def remove_whitespace(s):
    """ Remove white space and tabs from a string """
    s = s.replace(' ',"")
    s = s.replace("\t","")
    return s
      
def hey(what):
    what = remove_whitespace(what) #remove whitespace (' ') and tabs ('\t') from what.
    if what == "": #Test for prolonged silence.
        return str('Fine. Be that way!')
    elif is_yelling(what): # Test for yelling
        return 'Whoa, chill out!'
    elif is_question(what): #Test if Bob is being asked a question, but not being yelled at.
        return str('Sure.')
    else: # We are left with a non-yelled, non-question statement.
        return str('Whatever.')
