#
# Skeleton file for the Python "Bob" exercise.
#
import string

def hey(what):

    question_reponse = 'Sure.'
    shout_response = 'Whoa, chill out!'
    nothing_response = 'Fine. Be that way!'
    catchall_response = 'Whatever.'

    what = what.strip()

    if what == '':
        return nothing_response

    if is_shouting(what):
        return shout_response

    # We consider shouted questions to be shouts
    if what[-1] == '?':
        return question_reponse

    return catchall_response

def is_shouting(text):

    # Letters are required for shouting
    has_letters_flag = False
    for c in text:
        if c in string.letters:
            has_letters_flag = True
            break
    if not has_letters_flag:
        return False

    shouting_version = text.upper()
    if text == shouting_version:
        return True

    return False
