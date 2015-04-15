# -*- coding: utf-8 -*-
import re


def hey(message):
    message = unicode(message)
    # Tell whether the given message is a question.
    is_question = message.endswith('?')

    # Get only the characters that matters, ie. Letters and digits.
    regex = re.compile(u'\W', re.U)
    message = regex.sub('', message)

    # Tell whether the given message has only numbers.
    try:
        int(message)
        only_numbers = True

    except ValueError:
        only_numbers = False

    # Check if the given input is empty.
    if not message:
        return 'Fine. Be that way!'

    # Check if the given input is a shout.
    elif not only_numbers and message == message.upper():
        return 'Whoa, chill out!'

    # Check if the given input is a question.
    elif is_question:
        return 'Sure.'

    # Return the default response instead.
    else:
        return 'Whatever.'
