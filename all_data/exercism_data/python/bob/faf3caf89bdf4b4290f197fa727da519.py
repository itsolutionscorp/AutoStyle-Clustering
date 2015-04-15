#
# A solution to the Python "Bob" exercise.
#


def hey(what):
    """Calculate Bob's response to a prompt.
    :param what: The input prompt.
    :return: Bob's reply
    """

    # Bob answers 'Whoa, chill out!' if you yell at him.
    if what == what.upper() and what.lower() != what.upper():
        return 'Whoa, chill out!'

    # Bob answers 'Sure.' if you ask him a question.
    if what.endswith('?'):
        return 'Sure.'

    # Bob says 'Fine. Be that way!' if you address him without actually saying
    # anything.
    if what.strip() == '':
        return 'Fine. Be that way!'

    # He answers 'Whatever.' to anything else.
    return 'Whatever.'
