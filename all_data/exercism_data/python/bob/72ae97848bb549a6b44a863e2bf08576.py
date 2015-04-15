#
# Answer for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    # default response
    response = 'Whatever.'

    if is_silence(what):
        response = 'Fine. Be that way!'
    elif is_shouting(what):
        response = 'Whoa, chill out!'
    elif is_question(what):
        response = 'Sure.'

    return response


def is_shouting(what):
    return what.isupper()

def is_silence(what):
    return what == ''

def is_question(what):
    return what.endswith('?')
