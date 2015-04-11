#
# Fourth implementation of "Bob" exercise.
#
def hey(what):

    what = what.strip()
    
    if what == '':           
        answer = 'Fine. Be that way!'
    elif what.isupper():                 
        answer = 'Whoa, chill out!'
    elif what.endswith('?'):
        answer = 'Sure.'
    else:
        answer = 'Whatever.'

    return answer
