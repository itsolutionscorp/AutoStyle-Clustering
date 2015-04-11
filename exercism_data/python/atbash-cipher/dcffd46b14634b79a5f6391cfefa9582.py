def encode(plain):
    plain = plain.lower()
    plaintext = "abcdefghijklmnopqrstuvwxyz"
    cipertext = plaintext[::-1]
    
    ciper = ""
    for char in plain:
        if char.isalpha():
            index = plaintext.index(char)
            ciper += cipertext[index]
        if char.isdigit():
            ciper += char
    
    cipers = []
    previous = 0
    while len(ciper):
        cipers.append(ciper[:5])
        ciper = ciper[5:]
    
    return " ".join(cipers)

def decode(cipher):
    cipher = "".join(cipher.split(" "))
    plaintext = "abcdefghijklmnopqrstuvwxyz"
    ciphertext = plaintext[::-1]
    
    plain = ""
    for char in cipher:
        index = ciphertext.index(char)
        plain += plaintext[index]
    
    return plain
