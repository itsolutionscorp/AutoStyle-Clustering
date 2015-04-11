#
# Skeleton file for the Python "Bob" exercise.
# Revision 2
#
def hey(what):
    # He answers 'Whoa, chill out!' if you yell at him.
    if what.isupper():
        return 'Whoa, chill out!'
    # Bob answers 'Sure.' if you ask him a question.
    # Rev 0 used indexing [-1:] instead of endswith(); updated thanks to Nitpick
    elif what.endswith('?'):
        return 'Sure.'
    # He says 'Fine. Be that way!' if you address him without actually saying anything.
    # Rev 0-1 used what.strip == ''; updated thanks to Nitpick
    elif not what.strip():
        return 'Fine. Be that way!'
    # He answers 'Whatever.' to anything else.
    else:
        return 'Whatever.'
