#
# Completed file for the Python "Bob" exercise.
#

DEFAULT_RESPONSE = u'Whatever.'
YELL_RESPONSE = u'Whoa, chill out!'
QUESTION_RESPONSE = u'Sure.'
BLANK_RESPONSE = u'Fine. Be that way!'


def hey(what):
    """
    Say hey to Bob! Don't expect a great response though...

    @param what: What is being said to Bob.
    @type what: unicode
    @return: Bob's response.
    @rtype: unicode
    """

    what = what.strip()

    if len(what) == 0:
        return BLANK_RESPONSE

    if what.isupper():
        return YELL_RESPONSE

    if what.endswith('?'):
        return QUESTION_RESPONSE

    return DEFAULT_RESPONSE
