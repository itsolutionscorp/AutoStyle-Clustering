import math
import re

@profile
def encode(phrase):
    phrase = phrase.lower()
    norm_phrase = re.sub(r'[^a-z0-9]', '', phrase)
    phrase_length = len(norm_phrase)
    if phrase_length == 0:
        return ''
    square_size = int(math.ceil(math.sqrt(phrase_length)))
    cols = math.ceil(phrase_length / square_size)
    phrase_padding = norm_phrase + ' ' * (square_size*cols - phrase_length)

    phrase_pieces = [phrase_padding[x:x+cols+1] for x in
                     range(0, phrase_length, square_size)]

    out = []

    for i in range(square_size):
        for j in phrase_pieces:
            out.append(j[i].strip())
        out.append(' ')
    return ''.join(out).strip()

if __name__ == '__main__':
    # encode('this')
    import random
    from string import ascii_lowercase
    n = 2000000
    s = ''.join(random.choice(ascii_lowercase) for z in range(n))
    e = encode(s)
    # print(e)
    # phrase = 'If man was meant to stay on the ground, !god would have given us roots'
    # phrase = phrase.lower()
    # new_phrase = re.sub(r'[^a-z0-9]', '', phrase)
    # print(new_phrase, len(new_phrase))
    # print(encode(phrase))
