import string
import re

CHUNK_SIZE = 5

# Build translation tables
letters = string.ascii_lowercase
table = string.maketrans(string.ascii_letters, letters[::-1]*2)

# Regex to match and strip unencodable characters
re_unencodable = re.compile(r'[\W_]')


def decode(text):
    return text.translate(table, ' ')


def encode(text):
    # Get a lower case alphanumeric version of the text
    text = re.sub(re_unencodable, '', text)
    encoded = text.translate(table)

    # Split into chunks
    chunks = [
        encoded[i:i+CHUNK_SIZE]
        for i in range(0, len(encoded), CHUNK_SIZE)
    ]
    return ' '.join(chunks)


if __name__ == '__main__':
    contents = open('pg2600.txt').read() * 10
    encoded = encode(contents)
