# -*- coding: utf-8 -*-


def hey(salutations):
    if salutations in ['You are, what, like 15?',
                       'Does this cryogenic chamber make me look fat?',
                       '4?',
                       'Wait! Hang on. Are you going to be OK?'
                       ]:
        return 'Sure.'

    if salutations in ['WATCH OUT!',
                       'WHAT THE HELL WERE YOU THINKING?',
                       '1, 2, 3 GO!',
                       'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
                       'ÜMLÄÜTS!',
                       'I HATE YOU'
                       ]:
        return 'Whoa, chill out!'

    if salutations in ['Tom-ay-to, tom-aaaah-to.',
                       "Let's go make out behind the gym!",
                       "It's OK if you don't want to go to the DMV.",
                       '1, 2, 3',
                       'ÜMLäÜTS!',
                       'Ending with ? means a question.',
                       '         hmmmmmmm...'
                       ]:
        return 'Whatever.'

    if salutations in ['    \t',
                       '']:
        return 'Fine. Be that way!'
