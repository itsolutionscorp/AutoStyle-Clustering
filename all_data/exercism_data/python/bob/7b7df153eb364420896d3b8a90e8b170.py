__author__ = 'romleinster'


def find_question(greeting):
    if greeting[-1] == '?':
        return True
    else:
        return False


def hey(salutation):
    if salutation.strip() == "":
        return 'Fine. Be that way!'
    else:
        if salutation.isupper():
            return 'Woah, chill out!'
        if find_question(salutation):
            return 'Sure.'
        else:
            return 'Whatever.'
