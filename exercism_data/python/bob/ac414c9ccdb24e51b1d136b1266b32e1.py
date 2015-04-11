# -*- coding: utf-8 -*-

from __future__ import unicode_literals


def hey(input_text):
    """
    Return bobs answer given the input text as follows:

    Return 'Whoa, chill out!' if the text is uppercase but not lowercase.
    Return 'Sure.' if the input is a question.
    Return 'Fine. Be that way!' if the input consists of whitespaces only.
    Return 'Whatever.' on all other cases.
    """

    if input_text == input_text.upper() != input_text.lower():
        return 'Whoa, chill out!'

    if input_text.endswith('?'):
        return 'Sure.'

    if len(input_text.strip()) == 0:
        return 'Fine. Be that way!'

    return 'Whatever.'
