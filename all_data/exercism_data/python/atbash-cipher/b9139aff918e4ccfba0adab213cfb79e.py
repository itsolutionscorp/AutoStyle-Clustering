trans_key = ''.maketrans('abcdefghijklmnopqrstuvwxyz',
                         'zyxwvutsrqponmlkjihgfedcba', ' ')


def encode(message):
    # extract only alphanumerics, remove capitalization, and translate using the key
    translation = ''.join([c for c in message if c.isalnum()]).lower().translate(trans_key)

    # group characters 5 at a time to meet our requirements for the encoded message
    return ' '.join([translation[x:x + 5] for x in range(0, len(translation), 5)])


def decode(message):
    return message.translate(trans_key)
