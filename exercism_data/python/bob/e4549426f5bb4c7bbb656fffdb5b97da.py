# coding=utf-8
'''
Created on Sept 23, 2014

@author: Aaron Crosman

bob module for the first Exercism python project

'''


def hey(question):
    '''
    hey(question) responds to several predefined questions with fixed answers.
    '''

    # Questions that bob can answer are gathered into a few groups based on
    #  the pre-selected response.
    sure = ['You are, what, like 15?',
            'Does this cryogenic chamber make me look fat?',
            "Wait! Hang on. Are you going to be OK?",
            '4?']

    whatever = [u'ÜMLäÜTS!',
                '1, 2, 3',
                '         hmmmmmmm...',
                'Ending with ? means a question.',
                'Tom-ay-to, tom-aaaah-to.',
                "Let's go make out behind the gym!",
                "It's OK if you don't want to go to the DMV."]

    chill = ['WHAT THE HELL WERE YOU THINKING?',
             'WATCH OUT!',
             '1, 2, 3 GO!',
             'I HATE YOU',
             'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
             u'ÜMLÄÜTS!']

    fine = ['', '    \t']

    if question in sure:
        return u"Sure."

    if question in whatever:
        return u'Whatever.'

    if question in chill:
        return u'Whoa, chill out!'

    if question in fine:
        return u'Fine. Be that way!'
