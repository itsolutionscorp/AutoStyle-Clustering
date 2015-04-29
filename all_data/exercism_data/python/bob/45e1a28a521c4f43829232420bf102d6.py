#!/usr/bin/env python3
# -*- coding: utf-8 -*-

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what=None):
    """hey Bob"""
    def umlaut(what):
        """ check for ulauts in string"""
        umlauts = 'äüö'
        what = what.lower()
        for i in umlauts:
            if what.find(i) != -1:
                return True
        return False


    def question(what):
        """ detect question"""
        if what[-1] == '?':
            return True
        return False


    def exclamation(what):
        """ detect exclamation"""
        if what[-1] == '!':
            return True
        return False


    def statement(what):
        """ detect statement"""
        if what[0].isupper() & (what[-1] == '.'):
            return True
        return False


    def end_white(what):
        """ ends with space"""
        if what[-1] == ' ':
            return True
        return False


    def start_white(what):
        """ starts with space"""
        if what[0] == ' ':
            return True
        return False


    def only_white(what):
        """ only whitespace"""
        for i in what:
            if (i != ' ') & (i != '\t'):
                return False
        return True


    def strip_items(string, items):
        """ remove chars in items"""
        for i in items:
            string = string.replace(i, '')
        return string


    def string_is_numeric(what):
        """ string is only numeric when slected signs are removed"""
        return strip_items(what, ' ,.?!').isnumeric()


    # first test if input is none
    if what is None: return

    # input is empty
    if what == '': return 'Fine. Be that way!'

    # white
    if only_white(what): return 'Fine. Be that way!'
    if end_white(what): return 'Sure.'
    if start_white(what): return 'Whatever.'

    # only caps
    if what.isupper(): return 'Whoa, chill out!'

    # check last character
    # question
    if question(what): return 'Sure.'
    if exclamation(what): return 'Whatever.'

    # umlaut
    if umlaut(what): return 'Whatever.'

    # statement
    if statement(what): return 'Whatever.'

    # is numeric
    if string_is_numeric(what): return 'Whatever.'
