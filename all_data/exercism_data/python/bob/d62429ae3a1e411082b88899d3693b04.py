__author__ = 'Dalton'


def hey(sentence):

    if sentence.isspace() or sentence == '':
        return 'Fine. Be that way!'
    elif sentence.isupper():
        return 'Whoa, chill out!'
    elif sentence[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
