#
# Skeleton file for the Python "Bob" exercise.
#

def is_question(s):
    """ Checks if last character is a question mark """
    if s[len(s)-1] == '?':
        return True
    else:
        return False
        
def is_alpha(s):
    """ Check if a string contains letters """
    for c in s:
        if c.lower() in "abcdefghijklmnopqrstuvwxyz":
            return True
    return False
    
def is_yelling(s):
    """ Checks if statement contains letters and if they are all caps """
    if s.upper() == s and is_alpha(s):
        return True
    else:
        return False
        
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
