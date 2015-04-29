# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(context):
    """ Bob answers 'Sure.' if you ask him a question.
        He answers 'Whoa, chill out!' if you yell at him.
        He says 'Fine. Be that way!' if you address him without actually
        saying anything.
        He answers 'Whatever.' to anything else.
    """

    stripped = context.strip()
    if not stripped:
        return 'Fine. Be that way!'
    elif context.isupper():
        return 'Whoa, chill out!'
    elif stripped.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
