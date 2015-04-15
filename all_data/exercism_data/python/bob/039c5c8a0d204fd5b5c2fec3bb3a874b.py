#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    Answer = ''
    if what == what.upper() and any(s.isalpha() for s in what):
        Answer = 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        Answer = "Sure."
    elif what.isspace() or what == '':
        Answer = "Fine. Be that way!"
    else:
        Answer = "Whatever."
    return Answer
