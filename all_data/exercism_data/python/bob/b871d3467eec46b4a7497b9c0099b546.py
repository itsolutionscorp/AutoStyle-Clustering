#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    assert type(what) == str
    # get rid of unsignificant whitespace
    what = what.strip()
    if what == "":
        # silence
        return 'Fine. Be that way!'
    if what.isupper():
        # that would be shouting
        return 'Whoa, chill out!'
    if what.endswith('?'):
        # that would be a question
        return "Sure."
    else:
        # meh.
        return 'Whatever.'
