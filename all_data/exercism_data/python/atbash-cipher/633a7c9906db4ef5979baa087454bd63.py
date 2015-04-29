import string

# global ciphers
ALPHABET = "abcdefghijklmnopqrstuvwxyz"
CYPHER = dict(zip([x for x in ALPHABET], [y for y in ALPHABET[::-1]]))
DECODER = dict(zip([x for x in ALPHABET[::-1]], [y for y in ALPHABET]))

def encode(phrase):
    code = ""
    count = 1
    for x in phrase.lower():
        if x in string.letters:
            code += CYPHER[x]
            count += 1
        elif x in string.digits:
            code += x
            count += 1
        if count == 6:
            count = 1
            code += " "
    return code.strip()

def decode(code):
    phrase = ""
    for x in code:
        if x != " ":
            phrase += DECODER[x]
    return phrase
