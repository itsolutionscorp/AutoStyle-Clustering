plain = "abcdefghijklmnopqrstuvwxyz"
cipher = "zyxwvutsrqponmlkjihgfedcba"
cipher_dict = { plain: cipher for plain, cipher in zip(plain, cipher) }

def atbash(inp):
    output = ''
    for char in inp.lower():
        if char in cipher_dict:
            output += cipher_dict[char]
        elif char.isdigit():
            output += char
    return output

def encode(plaintext):
    return group(atbash(plaintext))

def group(ciphertext):
    GROUPS_OF = 5
    grouped_text = [ciphertext[i : i+GROUPS_OF] 
                    for i in range(0, len(ciphertext), GROUPS_OF)]
    return ' '.join(grouped_text)    

def decode(ciphertext):
    return atbash(ciphertext)
