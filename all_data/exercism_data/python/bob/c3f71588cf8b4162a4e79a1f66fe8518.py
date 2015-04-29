#
# Python "Bob" exercise.
#

# hey method should receive a string as the argument.


def hey(what):
    #  remove  whitespace from string argument
    what = what.strip()

    # check is string matches a pattern and return a response
    if len(what) == 0:
        return "Fine. Be that way!"
    elif what.isupper():
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    else:
        return "Whatever."
