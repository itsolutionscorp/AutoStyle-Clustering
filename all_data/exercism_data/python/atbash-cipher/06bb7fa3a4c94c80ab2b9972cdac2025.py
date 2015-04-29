# Atbash Cipher Python exercism, 1st iteration

import string

cipher = string.maketrans(string.ascii_lowercase,string.ascii_lowercase[::-1])

def remove_punctuation(s):
    return s.translate(None, string.punctuation)
    
def remove_whitespace(s):
    return s.replace(' ','')
    
def add_space(s,n):
    """ Returns a string equal to s, but with a space added after every n characters """
    t = ""
    for i in xrange(len(s)):
        # Add white space after every n characters.
        if i % n == 0 and i != 0:
            t += ' '
        t += s[i]

    return t

def encode(plain):
    """ Create Atbash Cipher text from plain text """
    # Remove whitespace and punctionation
    encoded = remove_punctuation(plain.lower())
    encoded = remove_whitespace(encoded)
    
    # Add space after every 5 characters
    encoded = add_space(encoded, 5)
    
    # Use the cipher translation
    encoded = encoded.translate(cipher)
    
    return encoded
    
def decode(encoded):
    """ Decode ciphered text """
    # Remove white space from text
    plain = remove_whitespace(encoded)
    
    # Use the cipher translation
    plain = plain.translate(cipher)
    return plain
