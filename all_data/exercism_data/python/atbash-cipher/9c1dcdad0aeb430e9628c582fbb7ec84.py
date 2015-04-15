from string import (maketrans, ascii_letters,
                    ascii_lowercase, punctuation)
from itertools import izip_longest
atbash = maketrans(ascii_letters, ascii_lowercase[::-1] * 2)
size_of_bite = 5
delimiter = ' '


def decode(code):
    return code.translate(atbash, delimiter)


def encode(word):
    cipher = word.translate(atbash, delimiter + punctuation)
    chunk = [iter(cipher)] * size_of_bite
    chunks_of_tuples = izip_longest(*chunk, fillvalue='')
    chunks_of_strings = map(''.join, chunks_of_tuples)
    return delimiter.join(chunks_of_strings)
