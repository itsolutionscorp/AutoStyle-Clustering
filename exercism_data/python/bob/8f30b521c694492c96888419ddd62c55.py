#!/usr/bin/python2
# Skeleton file for the Python "Bob" exercise.


def is_yelling(what_list):
    '''This function detrmines you're yelling if
    'what_list' is NOT empty and ALL the alpha tokens
    (stripped from special chars ?!.;,) are UPPERCASE'''
    empty = True
    for word in what_list:
        if word.strip("!?.;,").isalpha():
            empty = False
            if not word.isupper():
                return False
    return True if not empty else False


def is_addressing_me_quietly(what_list):
    '''This in fact deduces you're addressing me quietly by exclusion
    if the what_list is NON empty and we arrived here. So probably
    this function is useless and has a malformed name'''
    return True if what_list == [] else False


def is_a_question(what_list):
    "Verify if ending with a question mark and NOT empty"
    return what_list and what_list[-1].endswith("?")


def hey(what):
    '''The main entry method: at first strips blanks and split the 'what'
    into a list of token; then using helper functions decides
    what to answer back'''
    what_list = what.strip().split()
    if is_yelling(what_list):
        return "Woah, chill out!"
    elif is_a_question(what_list):
        return "Sure."
    elif is_addressing_me_quietly(what_list):
        return "Fine. Be that way!"
    else:
        return "Whatever."