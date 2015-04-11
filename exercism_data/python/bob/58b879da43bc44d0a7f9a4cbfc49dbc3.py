#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    what = what.rstrip()
    
    if(what.isupper() == True):
        return u'Whoa, chill out!'

    elif(what.endswith(u'?') == True):
        return u'Sure.'

    elif( len(what) == 0 ):
        return u'Fine. Be that way!'
    
    else:
        return u'Whatever.'
