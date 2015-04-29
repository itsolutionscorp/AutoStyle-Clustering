#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    # The order of tests are important.
    # A shouted question is classified a shout, and not a question.
    # So, testing for a shout must come before testing for a question.
    #
    # Is the input shouted?
    if what.isupper():
        return 'Whoa, chill out!'
    # Is the input a question?
    elif what.endswith('?'):
        return 'Sure.'
    # Is the input not saying anything?
    elif not what:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
