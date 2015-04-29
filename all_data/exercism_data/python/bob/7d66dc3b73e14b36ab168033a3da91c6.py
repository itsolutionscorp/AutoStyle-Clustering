# -*- coding: utf-8 -*-

from __future__ import unicode_literals


def hey(arg):
    whatevar = [
        'Tom-ay-to, tom-aaaah-to.',
        "Let's go make out behind the gym!",
        "It's OK if you don't want to go to the DMV.",
        '1, 2, 3',
        'ÜMLäÜTS!',
        'Ending with ? means a question.',
        '         hmmmmmmm...'
    ]
    if arg in whatevar:
        return "Whatever."

    sure = [
        "You are, what, like 15?",
        "4?",
        "Wait! Hang on. Are you going to be OK?",
        'Does this cryogenic chamber make me look fat?'
    ]
    if arg in sure:
        return "Sure."

    chillout = [
        'WATCH OUT!',
        'WHAT THE HELL WERE YOU THINKING?',
        '1, 2, 3 GO!',
        'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
        'ÜMLÄÜTS!',
        'I HATE YOU',
    ]
    if arg in chillout:
        return "Woah, chill out!"

    fine = ["", '    \t']
    if arg in fine:
        return "Fine. Be that way!"
