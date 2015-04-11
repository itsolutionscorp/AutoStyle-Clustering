#-------------------------------------------------------------------------------
# Name:        mcface3000
# Purpose:      Becoming Alan Turing
#-------------------------------------------------------------------------------
import string
alphabet = 'abcdefghijklmnopqrstuvwxyz'

cipher = alphabet[::-1]

numbers = '123456789'

def encode(one):
    c_word = ''
    s = ' '
    count = 0
    one = looper(one)
    one = one.lower()
    one = one.translate(None, "  , .")
    one = one.translate(None, string.punctuation)
    for i in one:
        if i in numbers:
            c_word += i
        else:
            position = alphabet.find(i)
            c_word += cipher[position]
    c_word = looper(c_word)
    return c_word
def decode(two):
    d_word = ''
    two = two.translate(None, ' ')
    for i in two:
        position = cipher.find(i)
        d_word += alphabet[position]
    return d_word
def looper(test):
    count = 0
    greatest_hits = ''
    test = test.translate(None, ' ')
    for i in test:
        greatest_hits += i
        if (count == 4):
            greatest_hits += ' '
            count = 0
        else:
            count += 1
    if greatest_hits[-1] == ' ':
        greatest_hits = greatest_hits[:-1]
    return greatest_hits
