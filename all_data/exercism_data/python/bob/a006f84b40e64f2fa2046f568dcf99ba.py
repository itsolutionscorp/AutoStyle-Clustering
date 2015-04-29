#!/usr/bin/env python

import re

def hey(comment_to_bob):
    try:
        if (re.match(r'\s', comment_to_bob[0])) and (re.match(r'\S', comment_to_bob[-1])):
            return 'Whatever.'
        elif re.match(r'\d,', comment_to_bob) and not re.match(r'\d[,\w\s]*!$', comment_to_bob):
            return 'Whatever.'
        elif re.match(r'\s', comment_to_bob):
            return 'Fine. Be that way!'
        elif re.match(r'\d\?', comment_to_bob):
            return 'Sure.'
        elif (comment_to_bob[-1]) and (comment_to_bob == comment_to_bob.upper()):
            return 'Whoa, chill out!'
        elif comment_to_bob[-1] == '?':
            return 'Sure.'
        elif comment_to_bob[-1] == '!':
            return 'Whatever.'
        else:
            return 'Whatever.'
    except IndexError:
        return 'Fine. Be that way!'


def main():
    pass


if __name__ == '__main__':
    main()
