#
# Answer for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    # default response
    response = 'Whatever.'

    # silence
    if what == '':
        response = 'Fine. Be that way!'
    # shouting
    elif what.isupper():
        response = 'Whoa, chill out!'
    # question
    elif what[-1] == '?':
        response = 'Sure.'

    return response
