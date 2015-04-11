#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    questioned = 'Sure.'
    yelled = 'Whoa, chill out!'
    addressed = 'Fine. Be that way!'

    if what is '':
        return addressed

    lastLetter = what[-1]

    question = lastLetter == "?"
    yell = lastLetter == "!"
    capitalized = what.isupper()

    if (not question and not yell) and capitalized:
        return "Whoa, chill out!"
    elif not capitalized and yell:
        return "Whatever."
    elif question and capitalized:
        return yelled
    elif question:
        return questioned
    elif yell:
        return yelled
    else:
        return "Whatever."
