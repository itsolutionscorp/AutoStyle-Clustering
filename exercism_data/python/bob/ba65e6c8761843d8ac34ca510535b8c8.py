#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # He answers 'Whoa, chill out!' if you yell at him.
    if what.isupper():
        return 'Whoa, chill out!'
    # Bob answers 'Sure.' if you ask him a question.
    elif what[-1:] == '?':
        return 'Sure.'
    # He says 'Fine. Be that way!' if you address him without actually saying anything.
    elif what.strip() == '':
        return 'Fine. Be that way!'
    # He answers 'Whatever.' to anything else.
    else:
        return 'Whatever.'
