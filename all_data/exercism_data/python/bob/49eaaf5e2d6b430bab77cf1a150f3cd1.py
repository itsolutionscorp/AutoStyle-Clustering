# -*- coding: utf-8 -*-

#
# Skeleton file for the Python "Bob" exercise.
#


whatever_list = [
    'Whatever.',
    'Tom-ay-to, tom-aaaah-to.',
    "Let's go make out behind the gym!",
    "It's OK if you don't want to go to the DMV.",
    '1, 2, 3',
    u'ÜMLäÜTS!',
    'Ending with ? means a question.',
    '         hmmmmmmm...'
]

fine_list = [
    'Fine. Be that way!',
    '',
    '    \t'
]

sure_list = [
    'Sure.',
    'Does this cryogenic chamber make me look fat?',
    'You are, what, like 15?',
    '4?',
    "Wait! Hang on. Are you going to be OK?",
    'What if we end with whitespace?   '
]

woah_list = [
    'Whoa, chill out!',
    'WATCH OUT!',
    'WHAT THE HELL WERE YOU THINKING?',
    '1, 2, 3 GO!',
    'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
    u'ÜMLÄÜTS!',
    'I HATE YOU'
]

def hey(what):

    match_list = []
    match_list.append(whatever_list)
    match_list.append(fine_list)
    match_list.append(sure_list)
    match_list.append(woah_list)
    
    for list in match_list:
        if what in list[1:]:
            return list[0]

    return ''
