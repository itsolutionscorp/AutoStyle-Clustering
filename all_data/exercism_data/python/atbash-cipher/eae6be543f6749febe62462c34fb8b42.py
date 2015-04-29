import re
import string

cipher = str.maketrans(string.ascii_lowercase,
                       string.ascii_lowercase[::-1],
                       string.punctuation + string.whitespace)


def encode(cleartext: string):
    return re.sub("(.{5})", "\\1 ", apply_atbash_translation(cleartext), 0).strip()


def decode(ciphertext: string):
    return apply_atbash_translation(ciphertext)


def apply_atbash_translation(text: string) -> string:
    return text.lower().translate(cipher)
