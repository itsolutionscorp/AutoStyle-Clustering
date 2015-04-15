__author__ = 'tracyrohlin'

import utils

def encode(string):
    string = string.lower()
    string = string.replace(" ", "")
    string = string.replace(",", "")
    string = string.replace(".","")
    reverse_string = []
    alphabet = list("zyxwvutsrqponmlkjihgfedcba")
    reverse_alph = alphabet[:]
    reverse_alph.reverse()
    range_of_numbers = ['0', "1", "2", "3", "4", "5", '6', "7", "8", '9']
    while string:
        new = string[:5]
        new_list = []
        for char in new:
            for n in range(len(reverse_alph)):
                if char == alphabet[n]:
                    new_list.append(reverse_alph[n])
                elif char in range_of_numbers:
                    new_list.append(char)
                    break
        reverse_string.append("".join(new_list))
        string = string[5:]
    result = " ".join(reverse_string)
    return result

def decode(string):
    string = string.lower()
    string = string.replace(" ", "")
    string = string.replace(",", "")
    string = string.replace(".","")
    reverse_string = []
    alphabet = list("abcdefghijklmnopqrstuvwxyz")
    reverse_alph = alphabet[:]
    reverse_alph.reverse()
    range_of_numbers = ['0', "1", "2", "3", "4", "5", '6', "7", "8", '9']
    while string:
        new = string[:5]
        new_list = []
        for char in new:
            for n in range(len(reverse_alph)):
                if char == alphabet[n]:
                    new_list.append(reverse_alph[n])
                elif char in range_of_numbers:
                    new_list.append(char)
                    break
        reverse_string.append("".join(new_list))
        string = string[5:]
    result = "".join(reverse_string)
    return result

print decode("zmlyh gzxov rhlug vmzhg vkkrm thglm v")
