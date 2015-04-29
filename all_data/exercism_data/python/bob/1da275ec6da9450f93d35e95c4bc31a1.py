# -*- coding: utf-8 -*-

def hey(inputText):
    if inputText == '' or inputText.isspace():
        return 'Fine. Be that way!'
    elif unicode(inputText).isupper():
        return 'Whoa, chill out!'
    elif inputText[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
