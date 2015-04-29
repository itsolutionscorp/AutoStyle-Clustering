#!/usr/bin/python
'''
Bob answers 'Sure.' if you ask him a question.

He answers 'Woah, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.
'''




def hey(s):

    s = s.strip()

    if s == '':
        return 'Fine. Be that way!'

    elif s.isupper():
        return 'Woah, chill out!'

    elif s.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
