#
# Skeleton file for the Python "Bob" exercise.
#
import string

def hey(what):
    if is_empty(what):
        return 'Fine. Be that way!'
    if is_question(what):
        return 'Sure.'
    if is_yelling(what):
        return 'Whoa, chill out!'
    return 'Whatever.'

def is_empty(what):
    response = None
    # truely empty
    if len(what) == 0:
        response = True
    # just spaces
    if what.isspace():
        response = True
    return response 

def is_question(what):
    response = None
    # when there is just number with question mark
    if len(what) == 2:
        if what[0].isdigit() and what[1] == '?':
            response = True
        else:
            response = False
    else:
        # when normal question
        if what[1:].islower() and what[-1] == '?':
            response = True
        else:
            # when more sentences
            if '.' in what[:-1]:
                last_sentence = what.split('.')[-1]
                # we split and check again
                if len(last_sentence) > 1:
                    response = is_question(last_sentence)
                else:
                    response = False
            else:
                # if divided sentence is uppercase question, a.k.a yelling
                if what[-1] == '?' and not what.isupper():
                    response = True
                else:
                    response = False
    return response
    
def is_yelling(what):
    # when all uppercase
    if what.isupper():
        return True
    else:
        return False
