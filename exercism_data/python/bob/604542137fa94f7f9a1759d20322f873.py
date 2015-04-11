__author__ = 'romleinster'

from string import ascii_lowercase


def number_check(greeting):
    letters = [letter for letter in ascii_lowercase if letter in greeting.lower()]
    if len(letters) == 0:
        return True
    else:
        return False


def find_question(greeting):
    if greeting[-1] == '?':
        return True
    else:
        return False


def hey(salutation):
    if salutation.strip() == "":
        return 'Fine. Be that way!'
    else:
        if number_check(salutation):
            if find_question(salutation):
                return 'Sure.'
            else:
                return 'Whatever.'
        else:
            if salutation.upper() == salutation:
                return 'Woah, chill out!'
            if find_question(salutation):
                return 'Sure.'
            else:
                return 'Whatever.'
