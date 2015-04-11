# -*- coding: utf-8 -*-


class bob(object):
    def hey(inputText):

        inputText = inputText.strip(' \t\n\r')

        if inputText.isupper():
            yelling = True
        else:
            yelling = False

        umlauts = ['ä', 'ö', 'ü', 'ß', 'Ä', 'Ö', 'Ü']

        if any(e in inputText for e in umlauts):
            if yelling is True:
                return 'Woah, chill out!'
            else:
                return 'Whatever.'

        if inputText != '':
            text = inputText[-1]
        else:
            text = ''

        if '!' in text or yelling is True:
            return 'Woah, chill out!'
        elif '?' in text:
            return 'Sure.'
        elif text == '':
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
