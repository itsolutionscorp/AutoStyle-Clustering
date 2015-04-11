#William Morris
#exercism.io
#atatbash_cipher.py

import string

def decode(cipher):
    cipher_list = [letter.lower() for letter in cipher if letter.isalnum()]
    decoded_phrase = ''
    for letter in cipher_list:
        if letter.isalpha():
            index = string.ascii_lowercase.index(letter)
            reverse_letter = string.ascii_lowercase[-index-1]
        else:
            reverse_letter = letter
        decoded_phrase += reverse_letter
    return decoded_phrase

def encode(phrase):
    letter_list = [letter.lower() for letter in phrase if letter.isalnum()]
    sub_phrase = ''
    for letter in letter_list:
        if letter.isalpha():
            index = string.ascii_lowercase.index(letter)
            reverse_letter = string.ascii_lowercase[-index-1]
        else:
            reverse_letter = letter
        sub_phrase += reverse_letter
    encoded_phrase = ''
    for spaces in range(0,len(sub_phrase),5):
        if (spaces+5)>=len(sub_phrase):
            encoded_phrase =encoded_phrase + sub_phrase[spaces:]
        else:
            encoded_phrase =encoded_phrase + sub_phrase[spaces:spaces+5] + ' ' 
    return encoded_phrase
