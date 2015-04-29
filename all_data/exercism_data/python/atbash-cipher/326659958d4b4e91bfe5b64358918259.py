from string import maketrans
import re

alphabet = "abcdefghijklmnopqrstuvwxyz"
rev_alphabet = "zyxwvutsrqponmlkjihgfedcba"
atbash_cipher_trans = maketrans(alphabet, rev_alphabet)

def encode(msg):
    # Puts message in lower case, translates it and removes the whitespace.
    msg = msg.lower().translate(atbash_cipher_trans).replace(" ","")
    # Punctuation is removed here so as to get accurate 5-blocking
    msg = re.sub(r"[.,]", "", msg)
    newmsg = ""
    for i in range(len(msg)):
        if i>0 and i%5 == 0:
            newmsg += " "
        newmsg += msg[i]
    return newmsg

def decode(msg):
    return msg.lower().translate(atbash_cipher_trans).replace(" ","")
