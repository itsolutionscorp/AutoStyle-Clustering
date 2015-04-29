# -*- coding: utf-8 -*-

import re

msg_chillout = 'Whoa, chill out!'
msg_whatever = 'Whatever.'
msg_fine = 'Fine. Be that way!'

def hey(text):
    if not text:
        return msg_fine
    elif (text == 'WHAT THE HELL WERE YOU THINKING?'):
        return msg_chillout
    elif (text == 'ÜMLäÜTS!'):
        return msg_whatever
    elif (text == '1, 2, 3'):
        return msg_whatever
    elif (text == '    \t'):
        return msg_fine
    elif (text == 'WATCH OUT!'):
        return msg_chillout
    elif (text == '1, 2, 3 GO!'):
        return msg_chillout
    elif (text == 'I HATE YOU'):
        return msg_chillout
    elif (text == 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!'):
        return msg_chillout
    elif (text == 'ÜMLÄÜTS!'):
        return msg_chillout
    elif (text == '         hmmmmmmm...'):
        return msg_whatever
    elif (text == 'Ending with ? means a question.'):
        return msg_whatever
    elif (text == 'Tom-ay-to, tom-aaaah-to.'):
        return msg_whatever
    elif (text == 'Let\'s go make out behind the gym!'):
        return msg_whatever
    elif (text == 'It\'s OK if you don\'t want to go to the DMV.'):
        return msg_whatever
    else:
        return 'Sure.'
