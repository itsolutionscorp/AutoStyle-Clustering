__author__ = 'Eric'

import string

plain = "abcdefghijklmnopqrstuvwxyz"
cipher = "zyxwvutsrqponmlkjihgfedcba"


def decode(input):
    input = input.lower()
    output = ''
    for letter in input:
        if letter in string.ascii_lowercase:
            index = cipher.find(letter)
            output += plain[index]
        if letter in string.digits:
            output += letter
    return output


def encode(input):
    input = input.lower()
    output = ''
    i=0
    for letter in input:
        if letter in string.ascii_lowercase:
            if i > 0 and i % 5 == 0:
                output += ' '
            index = plain.find(letter)
            output += cipher[index]
            i += 1
        if letter in string.digits:
            output += letter
            i += 1
    return output
