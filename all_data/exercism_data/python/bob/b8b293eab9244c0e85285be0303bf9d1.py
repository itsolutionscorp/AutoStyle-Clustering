#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if(not what.strip()):
        reply = 'Fine. Be that way!'
    elif(what.strip().isupper()):
        reply = 'Whoa, chill out!'
    elif(what.strip()[-1] == '?'):
        reply = 'Sure.'
    else:
        reply = 'Whatever.'
    return reply
