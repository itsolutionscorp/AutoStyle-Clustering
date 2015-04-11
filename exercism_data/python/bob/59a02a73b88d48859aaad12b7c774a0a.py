# -*- coding: utf-8 -*-

def hey(greeting_raw):
    """Bob answers 'Sure.' if you ask him a question.

    He answers 'Whoa, chill out!' if you yell at him.

    He says 'Fine. Be that way!' if you address him without actually saying
    anything.

    He answers 'Whatever.' to anything else."""

    greeting = greeting_raw.strip()

    msg_fine = 'Fine. Be that way!'
    msg_chillout = 'Whoa, chill out!'
    msg_sure = 'Sure.'
    msg_whatever = 'Whatever.'

    if not greeting:
        return msg_fine
    elif greeting.isupper():
        return msg_chillout
    elif greeting.endswith('?'):
        return msg_sure
    else:
        return msg_whatever
