#!/usr/bin/env python


def hey(arg):
    
    """ .strip() removes all excess whitespace at the beginning and end of the argument"""
    arg = arg.strip()
         
         
    if len(arg) == 0:
        return 'Fine. Be that way!'
    elif arg == arg.upper() and arg != arg.lower():
        return 'Whoa, chill out!'
    elif arg[len(arg)-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
