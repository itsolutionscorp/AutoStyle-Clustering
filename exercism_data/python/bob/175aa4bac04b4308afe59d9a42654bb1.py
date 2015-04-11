#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def hey(teenager):
    if teenager.strip(' \t\r\n') == '':
        return 'Fine. Be that way!'
    elif teenager.upper() == teenager and len([x for x in teenager.encode('punycode') if x.isalpha()]) > 0:
        return 'Whoa, chill out!'
    elif len(teenager) > 0 and teenager[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'

if __name__ == '__main__':
    print '%s' % ('This script is meant to be used this way.')
