from string import maketrans, punctuation
from textwrap import wrap

PLAIN_TO_CIPHER = maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba')
 
def encode(message):
    translated = message.lower().translate(PLAIN_TO_CIPHER, punctuation)
    joined = "".join(translated.split())
    return " ".join(wrap(joined, 5))

def decode(message):
    translated = message.translate(PLAIN_TO_CIPHER)
    return "".join(translated.split())
