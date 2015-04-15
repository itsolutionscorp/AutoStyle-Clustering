def encode(text_str):
    alphabet = "abcdefghijklmnopqrstuvwxyz"
    cipher = alphabet[::-1]
    code_str = ""
    n = 0
    for letter in text_str.lower():
        if letter.isalpha():
            if n == 5:
                code_str += " "
                n = 0
            position = alphabet.index(letter)
            code_str += cipher[position]
            n += 1
        elif letter.isdigit():
            if n == 5:
                code_str += " "
                n = 0
            code_str += letter
            n += 1
    return code_str 

def decode(code_str):
    alphabet = "abcdefghijklmnopqrstuvwxyz"
    cipher = alphabet[::-1]
    text_str = ""
    for letter in code_str.lower():
        if letter.isalpha():
            position = cipher.index(letter)
            text_str += alphabet[position]
        elif letter.isdigit():
            text_str += letter
    return text_str 
