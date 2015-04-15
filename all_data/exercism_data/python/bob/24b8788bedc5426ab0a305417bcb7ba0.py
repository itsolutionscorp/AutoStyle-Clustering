# coding=utf-8

from __future__ import unicode_literals

def hey(s):
    answers = {

        'Whatever.':[
            'Tom-ay-to, tom-aaaah-to.',
            "Let's go make out behind the gym!",
            '1, 2, 3','ÜMLäÜTS!',
            'Ending with ? means a question.'] ,

        'Whoa, chill out!': [
            'WATCH OUT!', 'WHAT THE HELL WERE YOU THINKING?',
            '1, 2, 3 GO!',
            'ÜMLÄÜTS!',
            'I HATE YOU'],

        'Sure.':[
            'Does this cryogenic chamber make me look fat?',
            'You are, what, like 15?',
            '4?',
            "Wait! Hang on. Are you going to be OK?"],

        'Fine. Be that way!':[
            '',
            '    \t',
            '         hmmmmmmm...']

    }
    for k, v in answers.iteritems():
        if s in v:
            return k



