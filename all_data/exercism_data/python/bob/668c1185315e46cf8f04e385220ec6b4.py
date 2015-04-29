# Bob answers 'Sure.' if you ask him a question.
# He answers 'Woah, chill out!' if you yell at him.
# He says 'Fine. Be that way!' if you address him without actually saying
# anything.
# He answers 'Whatever.' to anything else.

import re


def is_shouty(text):
    """

    Is this string all caps and contains letters?
    """
    return text.upper() == text and re.search('[a-zA-Z]', text)


def is_question(text):
    """

    str text: str
    """
    query_words = ['who', 'how', 'when', 'what', 'where']
    if text[-1] == '?':
        return True
    for word in query_words:
        if word in text.lower():
            return True
    return False


def hey(phrase):
    """

    :param phrase:
    """

    if phrase and not phrase.isspace():
        if is_shouty(phrase):
            return 'Woah, chill out!'
        if is_question(phrase):
            return 'Sure.'
        return 'Whatever.'
    else:
        return 'Fine. Be that way!'
