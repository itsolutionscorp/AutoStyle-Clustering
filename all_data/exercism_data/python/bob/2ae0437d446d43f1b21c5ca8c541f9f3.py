#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    x=''
    
    if len(what)==0:
        x ='Fine. Be that way!'
        
    elif what[-1]=='?':
        x ='Sure.'
        
    elif what[-1]== '!':
        x ='Whoa, chill out!'



    else:
        x= 'Whatever.'
        

    return x