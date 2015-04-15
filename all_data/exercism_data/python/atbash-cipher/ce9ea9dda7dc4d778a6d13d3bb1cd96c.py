import string

LENGTH = len(string.ascii_lowercase) - 1
CHUNK_SIZE = 5


def encode(phrase):
    new_phrase = []

    for letter in phrase.lower():
        if letter in string.ascii_lowercase:
            index = string.ascii_lowercase.index(letter)
            new_phrase.append(string.ascii_lowercase[abs(index - LENGTH)])
        elif letter in string.digits:
            new_phrase.append(letter)

    new_phrase = ''.join(new_phrase)
    return ' '.join([new_phrase[i:i + CHUNK_SIZE] for i in
                     range(0, len(new_phrase), CHUNK_SIZE)])


def decode(phrase):
    return encode(phrase).replace(' ', '')
