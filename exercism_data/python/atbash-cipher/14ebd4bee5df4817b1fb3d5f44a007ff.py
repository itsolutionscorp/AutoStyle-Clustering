PLAIN = "abcdefghijklmnopqrstuvwxyz"
CIPHER = "zyxwvutsrqponmlkjihgfedcba"
PUNCTUATIONS = '!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~ '

def encode(text):
    counter = 0
    cipher_text= ""
    for letter in text.lower():
        if letter not in PUNCTUATIONS:
        
            if counter == 5:
                 cipher_text += " "
                 counter = 0
                 
            counter += 1
            if letter.isdigit():
                cipher_text += letter
            else:
                cipher_text += CIPHER[PLAIN.index(letter)]
            
    return cipher_text
    
def decode(text):
    plain_text=""
    for letter in text:
        if letter != " " :
            plain_text += PLAIN[CIPHER.index(letter)]
    return plain_text
    
