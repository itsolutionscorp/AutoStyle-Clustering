#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    #possible responses to the bob.hey function
    strRespWhatever = 'Whatever.' 
    strRespShout = 'Whoa, chill out!' 
    strRespSure = 'Sure.' 
    strRespFine = 'Fine. Be that way!' 

    #TestableCharacters
    strQuestionMark = '?'

    #test one of four cases
    #1) Statement passed is all capitals = strRespShout
    #2) Ends with a question mark = strRespSure
    #3) Contains a lower case letter or number = StrRespWhatever
    #4) Everything else = strRespFine
    
    if what.isupper():
        return strRespShout
    elif what.endswith(strQuestionMark):
        return strRespSure
    else:
        for i in what:
            if i.islower():
                return strRespWhatever
                break
            elif i.isdigit():
                return strRespWhatever
                break
        return strRespFine
