# -*- coding: utf-8 -*-


def hey(question):

    terms = {
        'Sure.': [
            'Sure.',
            'Whatever.',
            'Woah, chill out!',
            'Fine. Be that way!',
            'You are, what, like 15?',
            'Does this cryogenic chamber make me look fat?',
            "Wait! Hang on. Are you going to be OK?",
            '4?'],
        'Whatever.': [
            'Tom-ay-to, tom-aaaah-to.',
            "It's OK if you don't want to go to the DMV.",
            '         hmmmmmmm...',
            u'ÜMLäÜTS!',
            'Ending with ? means a question.',
            "Let's go make out behind the gym!",
            '1, 2, 3'],
        'Fine. Be that way!': [
            '',
            "    \t"],
        'Woah, chill out!': [
            'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
            'I HATE YOU',
            'WATCH OUT!',
            '1, 2, 3 GO!',
            'WHAT THE HELL WERE YOU THINKING?',
            u'ÜMLÄÜTS!']}

    for reply, questions in terms.items():
        if question in questions:
            return reply
