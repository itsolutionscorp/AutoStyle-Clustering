from string import ascii_lowercase

trans_key = ''.maketrans(ascii_lowercase,
                         ascii_lowercase[::-1], ' ')


def encode(message):
    # extract only alphanumerics
    translation = ''.join([c for c in message if c.isalnum()])

    # remove caps and translate
    translation = translation.lower().translate(trans_key)

    # group characters 5 at a time to meet our requirements for the encoded message
    return ' '.join([translation[x:x + 5] for x in range(0, len(translation), 5)])


def decode(message):
    return message.translate(trans_key)
