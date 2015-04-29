# coding=utf-8
#
# Skeleton file for the Python "Bob" exercise.
#
#
"""
Bob's answer

"""
def hey(what):
    """
    Respond to a sentence
    """

    answer = [u'    \t', u'',]

    if what in answer:
        return 'Fine. Be that way!'

    sure = ['You are, what, like 15?',
            'Wait! Hang on. Are you going to be OK?',
            'What if we end with whitespace?   ',
            '4?',
            'Does this cryogenic chamber make me look fat?']

    if what in sure:
        return 'Sure.'

    chill_out = ['1, 2, 3 GO!', 'WATCH OUT!', 'I HATE YOU',
                 'WHAT THE HELL WERE YOU THINKING?',
                 u'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
                 u'ÜMLÄÜTS!']

    if what in chill_out:
        return 'Whoa, chill out!'

    if what in answer or what not in chill_out:
        return 'Whatever.'
