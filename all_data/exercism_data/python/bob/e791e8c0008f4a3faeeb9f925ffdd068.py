# Exercism Python "Bob" exercise.
# I haven't coded in a while....
#
import string

def hey(what):
    
    what=what.replace(' ', '') #strip out all spaces, we don't need 'em
    what=what.replace('\t', '') #strip out all \ts, because otherwise prolonged silence baffles me
    print what+"x" #debug text
    reply=""

    if what.isupper()==1:  #shouting takes precedence over questions
        reply="Whoa, chill out!"
    elif what[-1:]=="?":
        reply = "Sure."
    elif what=="":#should be contains no meaningful chars of the alphabet or nums, do I strip out esc seqs? that seems too specific
        reply="Fine. Be that way!"
    else:
        reply="Whatever."

    return reply
