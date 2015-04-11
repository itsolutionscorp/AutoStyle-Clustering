import string
import re

CHUNK_SIZE = 5

# Build translation tables
forward = string.ascii_lowercase
backward = forward[::-1]
decoder = string.maketrans(backward, forward)
encoder = string.maketrans(forward, backward)

# Regex to match and strip unencodable characters
re_unencodable = re.compile(r'\W_')


def decode(text):
    return text.translate(decoder, ' ')


def encode(text):
    # Get a lower case alphanumeric version of the text
    text = re.sub(re_unencodable, '', text.lower())
    encoded = text.translate(encoder)

    # Split into chunks
    chunks = [
        encoded[i:i+CHUNK_SIZE]
        for i in range(0, len(encoded), CHUNK_SIZE)
    ]
    return ' '.join(chunks)
