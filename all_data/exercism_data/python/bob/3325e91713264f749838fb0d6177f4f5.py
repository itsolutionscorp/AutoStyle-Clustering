#-*- coding: utf-8 -*-


def hey(inputstring):
    if silence(inputstring):
        return("Fine. Be that way!")
    elif shouting(inputstring):
        return("Whoa, chill out!")
    elif question(inputstring):
        return("Sure.")
    else:
        return('Whatever.')


def silence(inputstring):
    return inputstring.strip() == ''


def shouting(inputstring):
    return inputstring.isupper()


def question(inputstring):
    return inputstring.endswith('?')
