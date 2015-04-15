#atbash.py
#Keep it secret! Keep it safe!
import string

Plain = "abcdefghijklmnopqrstuvwxyz"
Cipher = "zyxwvutsrqponmlkjihgfedcba"


def encode(message):
    encoded = message.replace(" ", "").lower().translate(string.maketrans(Cipher, Plain))
    encoded = encoded.translate(string.maketrans("", ""), string.punctuation)
    encoded = " ".join(encoded[i:i+5] for i in range(0, len(encoded), 5))
    return encoded


def decode(message):
    decoded = message.replace(" ", "").lower().translate(string.maketrans(Plain, Cipher))
    return decoded
