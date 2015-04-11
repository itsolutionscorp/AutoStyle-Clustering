#
# Skeleton file for the Python "Bob" exercise.
#



def hey(what):
    length = len(what)

    if what.split()==[]:  
        return 'Fine. Be that way!'
    
    elif what==what.upper() and what!=what.lower():
        return 'Whoa, chill out!'
    
    elif what[length-1]=='?':
        return 'Sure.'

    return 'Whatever.'
