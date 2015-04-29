import string
import re
from enum import Enum

alphabet = "abcdefghijklmnopqrstuvwxyz"

class TransType(Enum):
    plainToCypher = 1
    cypherToPlain = 2


def encode(plaintext):
    transTable = atbash_translation_table(TransType.plainToCypher)

    plaintext = cleanInput(plaintext)
    cyphertext = plaintext.translate(transTable)
    # Now insert space every 5
    n = 5
    cypherIter = iter([cyphertext[i:i+n] for i in range(0, len(cyphertext), n)]) # splits cypher into chunks of 5
    return ' '.join(cypherIter)


def decode(cyphertext):
    transTable = atbash_translation_table(TransType.cypherToPlain)

    cyphertext = cleanInput(cyphertext)    
    plaintext = cyphertext.translate(transTable)
    return plaintext


# Returns the python translation table for the atbash cipher.
# Takes a single argument of enum type TransType to specify
# whether the table is for plain-to-cypher or cyper-to-plain
def atbash_translation_table(transType):
    if transType == TransType.plainToCypher:
        return string.maketrans(alphabet, alphabet[::-1])
    else:
        return string.maketrans(alphabet[::-1], alphabet)


def cleanInput(inputString):
    cleaned = inputString.lower()
    cleaned = re.sub(r'[^a-zA-Z0-9]','', cleaned)
    return cleaned
