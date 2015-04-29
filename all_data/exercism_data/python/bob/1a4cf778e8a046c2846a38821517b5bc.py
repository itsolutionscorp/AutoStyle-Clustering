#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    # Order is important. A shouted question is a shout, and not a question.
    # Is the input shouting.
    if what.isupper():
        return 'Whoa, chill out!'
    # Is the input a question.
    elif what.endswith('?'):
        return 'Sure.'
    # Is the input not saying anything.
    elif not what:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
