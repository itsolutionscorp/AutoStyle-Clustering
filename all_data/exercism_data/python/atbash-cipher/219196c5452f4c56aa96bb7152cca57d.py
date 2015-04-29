import string

ALPHABET = string.ascii_lowercase
GROUP_SIZE = 5


def encode(sentence):
    encrypted = _translate(sentence)
    formatted = []
    for index, character in enumerate(encrypted):
        formatted.append(character)
        if (index+1) % GROUP_SIZE == 0:
            formatted.append(' ')
    return ''.join(formatted).strip()


def decode(sentence):
    unformatted = [character for character in sentence if not character.isspace()]
    return ''.join(_translate(unformatted)).strip()


def _translate(sentence):
    destination = []
    for character in sentence:
        if character.isdigit():
            destination.append(character)
        elif character.lower() in ALPHABET:
            destination.append(ALPHABET[-ALPHABET.index(character.lower()) - 1])
    return destination
