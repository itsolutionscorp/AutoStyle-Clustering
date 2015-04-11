#
# Skeleton file for the Python "Bob" exercise.
# Shawn Fitzgibbons submitted 9/28/14
# email: shawn.fitzgibbons@gmail.com

def hey(what):
    
    if what.isupper(): #if string contains all caps
       return 'Whoa, chill out!' 
    elif what.endswith('?'): #if string ends with '?'
        return 'Sure.'
    elif what.isspace() or len(what) == 0: #if string is only spaces or empty
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
        
