""" Bob's answer.
    Excercism.io test 1
"""
import re

DEFAULT_ANSWER               = "Whatever."

QUESTION_DEFAULT_END_PATTERN = "?"
QUESTION_DEFAULT_ANSWER      = "Sure."

YELLING_DEFAULT_ANSWER       = "Whoa, chill out!"
YELLING_DEFAULT_NEEDED_CHARS = "[a-zA-Z]"  # for re.search

SILENCE_DEFAULT_STRING       = ""
SILENCE_DEFAULT_ANSWER       = "Fine. Be that way!"


def answer_question(default_answer=QUESTION_DEFAULT_ANSWER):
    """ :returns: the answer to a question
    """
    return default_answer


def answer_yelling(default_answer=YELLING_DEFAULT_ANSWER):
    """ :returns: the answer to a yelling
    """
    return default_answer


def answer_silence(default_answer=SILENCE_DEFAULT_ANSWER):
    """ :returns: the answer to a silence
    """
    return default_answer


def is_question(what, end_pattern=QUESTION_DEFAULT_END_PATTERN):
    """ :returns: True if <what> is a question
            a question is a string ending with <end_pattern>
        :param str what: a string
        :param end_pattern: a pattern (for endswith)
    """
    return what.endswith(end_pattern)


def is_yelling(what, needed_chars=YELLING_DEFAULT_NEEDED_CHARS):
    """ :returns: True if <what> is a yelling
            a yelling is upper case
            and contains at least one character from needed_char (regex)
        :param str what: a string
        :param str needed_chars: a regex of the chars that need to be in the 
            string : this because "1, 2, 3" is not yelling but "is uppercase"
    """
    return what == what.upper() and re.search(needed_chars, what)


def is_silence(what, silence=SILENCE_DEFAULT_STRING):
    """ :returns: True if <what> is a silence
            a silence is an empty string
        :param str what: a string
    """
    return what == silence


def clean(what):
    """ :returns: <what> cleaned
            * trimed (spaces and tabs)
        :param str what: the string to clean
    """
    return what.strip(" \t")


def hey(what, default_answer=DEFAULT_ANSWER):
    """ :returns: the bob's answer
        :param str what: what ppl said to bob
        :param str default_answer: the bob's answer to any unexpected sentence
    """
    clean_what = clean(what)

    answer = default_answer

    if is_silence(clean_what):
        answer = answer_silence()

    elif is_yelling(clean_what):
        answer = answer_yelling()

    elif is_question(clean_what):
        answer = answer_question()

    return answer
