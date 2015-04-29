#William Morris
#exercism.io
#atbash_cipher.py

import string

def makeCipherKey():
    alphabet = string.ascii_lowercase
    reverse_alphabet = string.join([string.ascii_lowercase[-string.ascii_lowercase.index(letter)-1]
                        for letter in string.ascii_lowercase],'')
    return string.maketrans(alphabet,reverse_alphabet)

def translate(phrase):
    table = makeCipherKey()
    remove_me = string.whitespace + string.punctuation
    return phrase.lower().translate(table, remove_me)
    

def decode(cipher):
    return translate(cipher)

def encode(phrase):
    sub_phrase = translate(phrase)
    encoded_cipher = ''
    for spaces in range(0,len(sub_phrase),5):
        if (spaces+5)>=len(sub_phrase):
            encoded_cipher =encoded_cipher + sub_phrase[spaces:]
        else:
            encoded_cipher =encoded_cipher + sub_phrase[spaces:spaces+5] + ' ' 
    return encoded_cipher
