atbash_transtable = str.maketrans("abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba")

def encode(cleartext):
    """Encode text using the Atbash Cipher"""

    #First get the text ready for encoding
    ciphertext = cleartext.lower()
    ciphertext = list(filter(lambda x: x.isalnum(), ciphertext))
    ciphertext = ''.join(ciphertext)

    #Next, encode the text using the Atbash Cipher
    ciphertext = str.translate(ciphertext, atbash_transtable)
    
    #Finally, return the string with spaces every 5 charactes
    return ' '.join(ciphertext[i:i+5] for i in range(0, len(ciphertext), 5))
    
def decode(ciphertext):
    """Decode text using the Atbash Cipher"""

    #First, clear any whitespace from the text
    cleartext = ''.join(ciphertext.split())

    #Next, decode the text using the Atbash Cipher
    cleartext = str.translate(cleartext, atbash_transtable)
    
    return cleartext
