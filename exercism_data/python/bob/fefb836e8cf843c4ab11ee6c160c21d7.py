#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    assert type(what) == str
    if what.strip() == "":
        # silence
        return 'Fine. Be that way!'
    # get rid of whitespace
    what = what.strip()
    alphas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    if what.upper() == what and any([c in what for c in alphas]):
        # that would be shouting
        return 'Whoa, chill out!'
    if what[-1] == "?":
        # that would be a question
        return "Sure."
    else:
        # meh.
        return 'Whatever.'
