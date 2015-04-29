plain = list('abcdefghijklmnopqrstuvwxyz')
cipher = list('zyxwvutsrqponmlkjihgfedcba')

def decode(code):
    ciphertext = []
    i = 0
    while i < len(code):
        if code[i].isalpha():
            j = 0
            while j < len(plain):
                if code[i] == cipher[j]:
                    ciphertext.append(plain[j])
                j+=1
        if code[i].isdigit():
            ciphertext.append(code[i])
        i+=1
    return ''.join(ciphertext) 

def encode(words):
    ciphertext = []
    i = 0
    while i < len(words):
        if words[i].isalpha():
            j = 0
            while j < len(plain):
                if words[i].lower() == cipher[j]:
                    ciphertext.append(plain[j])
                j+=1
        if words[i].isdigit():
            ciphertext.append(words[i])
        i+=1
    k = 1
    while k < len(ciphertext):
        if k % 5 == 0:
            ciphertext.insert(k + k / 5 - 1, ' ')
        k+=1
    while ciphertext[len(ciphertext) - 1] == ' ':
        del ciphertext[len(ciphertext) - 1]
    return ''.join(ciphertext)
