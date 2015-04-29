#
# Skeleton file for the Python "Bob" exercise.
#
#import re

def hey(what):
    # Bob answers 'Sure.' if you ask him a question.
    # answers 'Whoa, chill out!' if you yell at him.
    # says 'Fine. Be that way!' if you address him without actually 
    # saying anything
    # 'Whatever.' to anything else
    
    # Remove whitespace in string
    what = what.strip(" \t\n\r\f\v")
    #print "%s %r" % (what, what)

    if what == '':
        # if the input is just special characters or no input at all
        response = 'Fine. Be that way!'
    elif what.isupper():
        # using isupper() as pointed out by user AlbericC
        response = 'Whoa, chill out!'
    elif what.endswith("?"):
        # Given a question
        response = 'Sure.'
    else:
        # Unknown input
        response = 'Whatever.'

    return response
