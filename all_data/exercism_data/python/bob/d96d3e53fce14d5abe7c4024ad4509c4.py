#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    """
    Bob answers 'Sure.' if you ask him a question.
    He answers 'Whoa, chill out!' if you yell at him.
    He says 'Fine. Be that way!' if you address him without actually saying
    anything.
    He answers 'Whatever.' to anything else.
    """

    # trim whitespace
    # test for all caps
    # look for question mark at the end
    # check if it is empty
    what = what.strip()
    ans = ""
    if what.isupper():
        ans = 'Whoa, chill out!'
    elif what.endswith('?'):
        ans = 'Sure.'
    elif not what:
        ans = 'Fine. Be that way!'
    else:
        ans = 'Whatever.'
    return ans
