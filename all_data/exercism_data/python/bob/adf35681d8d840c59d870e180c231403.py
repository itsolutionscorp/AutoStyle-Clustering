#!/usr/bin/python
# -*- coding: utf-8 -*-

import re

def hey(query):
    if re.match('^\s*$', query):
        return 'Fine. Be that way!'
    elif re.search('[A-Z]+', query) and re.match(u'^[0-9A-Z\u00c0-\u00dc\%\^\@'
                                                  '\#\$\(\*\?\!\,\s]*$', query):
        return 'Whoa, chill out!'
    elif re.match('^.*\?$', query):
        return 'Sure.'
    else:
        return 'Whatever.'

def main():
    while True:
        query = raw_input('Say something to Bob: ')
        print bob(query)
    
if __name__ == '__main__':
    main()
