#
# OhMy's solution for the Python "Bob" exercise.
#
def hey(msg):
    msg = msg.strip()               # Deals with leading and trailing whitespace
    if msg == '':                   # Check for empty string first so we can be confident errors won't be thrown
        return 'Fine. Be that way!' 
    elif msg.isupper():             # Upper-case check next as it seems to take precedence over questions
        return 'Whoa, chill out!'
    elif msg[-1] == '?':            # Questions
        return 'Sure.'
    return 'Whatever.'              # Catch-all
