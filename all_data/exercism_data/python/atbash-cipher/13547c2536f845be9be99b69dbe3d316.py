import string

alph = string.ascii_lowercase
ciph = alph[::-1]

def decode(str):
    str = str.lower()
    s = ''

    for char in str:
        for index, char2 in enumerate(ciph):           
            if char == char2 and (char.isalpha()or char.isdigit()):
                s += alph[index] 
    return s
 
def encode(str):
    str = str.lower()
    s = ''

    for char in str:
        if char.isdigit():
            s += char
        for index, char2 in enumerate(alph):
            if char == char2 and char.isalpha():
                s += ciph[index]
    i = 0
    s2 = ''

    while i < len(s):
        if i % 5 == 0 and i != 0:
             s2 += ' '
             s2 += s[i]
        else:
             s2 += s[i]
        i += 1

    return s2 
