#!/usr/bin/python

def hey(input_string):
    input_string = input_string.strip()
    if input_string.isupper():
        return 'Woah, chill out!'
    elif input_string.endswith("?"):
        return 'Sure.'
    elif input_string.isspace() or input_string == "":
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
