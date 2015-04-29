from string import maketrans, punctuation

alphabet = "abcdefghijklmnopqrstuvwxyz"
reverse = "zyxwvutsrqponmlkjihgfedcba"

def encode(text):
    conversion = maketrans(alphabet, reverse)
    character_set = text.lower().replace(' ', '')
    character_set = character_set.translate(conversion, punctuation)

    for item in range(5,len(character_set)+1, 6):
        character_set = character_set[:item] + ' ' + character_set[item:]

    return character_set

def decode(text):
    conversion = maketrans(reverse, alphabet)
    character_set = text.replace(' ', '')
    character_set = character_set.translate(conversion, punctuation)

    return character_set
