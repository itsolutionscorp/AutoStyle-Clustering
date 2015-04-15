#!/usr/bin/python3


def hey(msg):
    """Bob's response"""
    answers = {
        'question': 'Sure.',
        'yell': 'Woah, chill out!',
        'empty': 'Fine. Be that way!',
        'else': 'Whatever.',
    }
    if msg.strip() == '':
        return answers['empty']
    elif msg.isupper():
        return answers['yell']
    elif msg[-1] == '?':
        return answers['question']
    else:
        return answers['else']
