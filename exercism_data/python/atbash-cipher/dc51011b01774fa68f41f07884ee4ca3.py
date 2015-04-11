"""
atbash_cipher - a module for Atbash ciphers.
"""

import string


# Translation globals.
ALPHABET = string.ascii_lowercase
CIPHER = ALPHABET[::-1]
ALPHABET += string.digits
CIPHER += string.digits
ENCODE_LUT = {a: c for a, c in zip(ALPHABET, CIPHER)}
DECODE_LUT = {c: a for c, a in zip(CIPHER, ALPHABET)}


def encode(string):
    """
    Encode a string into the Atbash cipher.
    """
    
    # Sanitize the input string.
    string = ''.join(string.split()).lower()
    string = ''.join(a for a in string if a.isalnum())
    
    # Encode the output string.
    encoded_string = ''.join(ENCODE_LUT[a] for a in string)
    formatted_string = ''.join(' ' + c if i % 5 == 0 and i >= 5 else c
                               for i, c in enumerate(encoded_string))
    return formatted_string


def decode(string):
    """
    Decode a string from the Atbash cipher.
    """
    
    # Remove the format spacing, and convert directly back to the alphabet.
    concatenated_string = ''.join(string.split())
    decoded_string = ''.join(DECODE_LUT[c] for c in concatenated_string)
    return decoded_string
