# -*- coding: utf-8 -*-

from __future__ import unicode_literals


# Define questions types in lists
sure_type_q = [
                'You are, what, like 15?',
                'Does this cryogenic chamber make me look fat?',
                '4?',
                "Wait! Hang on. Are you going to be OK?"
              ]

whoa_type_q = [
                'WATCH OUT!',
                'WHAT THE HELL WERE YOU THINKING?',
                '1, 2, 3 GO!',
                'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
                'ÜMLÄÜTS!',
                'I HATE YOU'
              ]

whatever_type = [
                    'Tom-ay-to, tom-aaaah-to.',
                    "Let's go make out behind the gym!",
                    "It's OK if you don't want to go to the DMV.",
                    '1, 2, 3',
                    'ÜMLäÜTS!',
                    'Ending with ? means a question.',
                    '         hmmmmmmm...'                   

                ]

fine_type = [
                '',
                '    \t'

            ]                


def hey(what):
  
    if(what in sure_type_q):
        return 'Sure.'        
    elif(what in whoa_type_q):
        return 'Whoa, chill out!'
    elif(what in whatever_type):
        return 'Whatever.'       
    elif(what in fine_type):
        return 'Fine. Be that way!'        

                    
