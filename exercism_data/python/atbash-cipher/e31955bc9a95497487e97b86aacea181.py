import string

ALPHABET = string.ascii_lowercase
REV_ALPHABET = ALPHABET[::-1]

ENCODE_TABLE = str.maketrans(ALPHABET, REV_ALPHABET)
DECODE_TABLE = str.maketrans(REV_ALPHABET, ALPHABET)


def code(str_to_code, table=ENCODE_TABLE):
    str_to_code = [char.lower() for char in str_to_code if char.isalnum()]
    coded_str = ''
    for letter in str_to_code:
        coded_str += letter.translate(table)
    return coded_str


def encode(str_to_encode):
    encoded_str = code(str_to_encode)
    chunks = []
    for num in range(0, len(encoded_str) + 1, 5):
        chunks.append(encoded_str[num : num + 5])
    return " ".join(chunks).strip()


def decode(str_to_decode):
    return code(str_to_decode, table=DECODE_TABLE)
