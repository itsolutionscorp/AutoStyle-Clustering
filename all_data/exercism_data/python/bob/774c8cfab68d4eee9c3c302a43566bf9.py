__author__ = 'romleinster'


def number_check(greeting):
    letters = []
    alphabet = 'abcdefghijklmnopqrstuvwxyz'
    for letter in alphabet:
        if letter in greeting.lower():
            letters.append(letter)
    if len(letters) == 0:
        return True
    else:
        return False


def find_question(greeting):
    if greeting[len(greeting)-1] == '?':
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
