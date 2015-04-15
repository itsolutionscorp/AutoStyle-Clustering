# -*- coding: utf-8 -*-

_BLANK = ''


class Bob(object):
    """Bob a lackadaisical teenager.

    In conversation, his responses are very limited.
    """
    def hey(self, phrase):
        phrase = _normalize(phrase)

        if phrase is _BLANK:
            return 'Fine. Be that way!'

        if _is_yelling(phrase):
            return 'Woah, chill out!'

        if _is_question(phrase):
            return 'Sure.'
        else:
            return 'Whatever.'


def _is_yelling(phrase):
    return phrase == phrase.upper()


def _is_question(phrase):
    return phrase.endswith('?')


def _normalize(phrase):
    return _BLANK if phrase is None else phrase.strip()
