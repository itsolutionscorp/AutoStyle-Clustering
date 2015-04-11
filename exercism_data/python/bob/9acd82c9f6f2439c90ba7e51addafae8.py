#
# Skeleton file for the Python "Bob" exercise.
#
from string import whitespace, ascii_uppercase

def hey(what):
    if (what != None and len(str(what)) >= 1): ##Something is being asked/said
        if (what == what.upper()): ##Yelling at him returns "Whoa, chill out!" (yelling signalled by all upper case?)
            for c in what:
                if c in ascii_uppercase:
                    return 'Whoa, chill out!'
        if (what[len(what)-1] == '?'): ##Asking him a question returns "Sure." (Questions signaled by question marks?)
            return 'Sure.'
        for c in what:
            if not (c in whitespace): ##ensuring that the string is not entirely whitespace. (this should catch whitespace characters like \t)
                return 'Whatever.' ##Non-questions and non-exclamations
    return "Fine. Be that way!" ##Addressing him without saying anything. 
