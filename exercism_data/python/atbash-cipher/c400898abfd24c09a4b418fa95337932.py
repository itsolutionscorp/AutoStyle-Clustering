# Atbash Cipher
import string

plain_letters = "abcdefghijklmnopqrstuvwxyz"
cipher_letters = "zyxwvutsrqponmlkjihgfedcba"

translation = dict(zip(plain_letters, cipher_letters))

def encode(plain_text):
    cipher_list = []
    output_list = []
    
    stripped_text = plain_text.replace(" ", "")
    stripped_text = stripped_text.lower()
    
    for punc in string.punctuation:
        if punc in stripped_text:
            stripped_text = stripped_text.replace(punc, "")

    for char in stripped_text:
        if char.isalpha():
            cipher_list.append(translation[char])
        else:
            cipher_list.append(char)
    
    for i in range(len(cipher_list)):
        output_list.append(cipher_list[i])
        if (i + 1) % 5 == 0 and i != len(cipher_list) - 1:
            output_list.append(" ")
            
    return "".join(output_list)

def decode(cipher_text):
    output_list = []
    
    stripped_text = cipher_text.replace(" ", "")
    
    for char in stripped_text:
        if char.isalpha():
            output_list.append(translation[char])
        else:
            output_list.append(char)
    
    return "".join(output_list)
