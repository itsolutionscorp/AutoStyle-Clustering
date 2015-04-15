from string import maketrans

plain = 'abcdefghijklmnopqrstuvwxyz'
reverse = 'zyxwvutsrqponmlkjihgfedcba'
delete = ' :.;,?!@#$%&()+=-*/_<> []{}`~^'
cipher = maketrans(plain, reverse)

def encode(str):
    cipher_str = str.lower().translate(cipher, delete)    
    return " ".join(cipher_str[i:i+5] for i in range(0, len(cipher_str), 5))

def decode(str):
    plain_str = str.lower().translate(cipher, delete)
    return plain_str
