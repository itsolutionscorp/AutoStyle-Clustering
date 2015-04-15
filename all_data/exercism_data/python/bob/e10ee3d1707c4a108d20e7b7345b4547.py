#
# Skeleton file for the Python "Bob" exercise.
#

QUESTION_RESPONSE = 'Sure.'
YELL_RESPONSE = 'Whoa, chill out!'
EMPTY_RESPONSE = 'Fine. Be that way!'
DEFAULT_RESPONSE = 'Whatever.'


def _is_empty(what):
    return what == ''


def _is_question(what):
    return what.endswith('?')


def _is_yell(what):
    return what.isupper()


def _sanitize_input(what):
    return what.strip()


def hey(what):
    """
    Bob is a lackadaisical teenager. In conversation, his responses are very limited.
    Bob answers 'Sure.' if you ask him a question.
    He answers 'Whoa, chill out!' if you yell at him.
    He says 'Fine. Be that way!' if you address him without actually saying anything.
    He answers 'Whatever.' to anything else.

    :param what: What you're saying to Bob.
    :return: Bob's response.
    """
    what = _sanitize_input(what)
    if _is_empty(what):
        return EMPTY_RESPONSE
    elif _is_yell(what):
        return YELL_RESPONSE
    elif _is_question(what):
        return QUESTION_RESPONSE
    else:
        return DEFAULT_RESPONSE
