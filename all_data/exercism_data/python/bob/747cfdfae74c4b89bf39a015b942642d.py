#!/usr/bin/env python
# coding: utf-8
import re
import unicodedata


class Bob(object):

    def hey(self, stuff):
        stripped_stuff = stuff.strip()
        if Bob.is_yelling(stripped_stuff):
            return 'Woah, chill out!'
        elif stripped_stuff.endswith('?'):
            return 'Sure.'
        elif stripped_stuff == '':
            return 'Fine. Be that way!'
        return 'Whatever.'

    @staticmethod
    def is_yelling(stuff):
        """
        :return boolean True if all letters in stuff are uppercased
        """
        letters = filter(lambda c: 'L' in unicodedata.category(c), unicode(stuff))  # 'L' category is for 'letter'
        if letters == u'':
            return False        
        return all(('u' in unicodedata.category(c) for c in letters))  # 'u' category is for 'uppercase'
        
