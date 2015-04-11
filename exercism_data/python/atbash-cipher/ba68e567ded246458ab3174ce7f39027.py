# -*- coding: utf-8 -*-

import string

sigma_alpha = "abcdefghijklmnopqrstuvwxyz"
delete_chars = " ,.-?!"

substitution = string.maketrans(sigma_alpha,
                                sigma_alpha[::-1])

def block(sequence, length):
    """
    block(sequence, int) -> list of slices

    Partitions the sequence int subsequences of given length and one shorter
    subsequence with the remaining items.
    """

    return [sequence[start : start + length]
            for start in range(0, len(sequence), length)]


def encode(plain):
    """
    encode(str) -> str

    Encodes the given string using the atbash cipher into blocks of 5
    characters separated by " ".
    """

    return " ".join(block(decode(plain), 5))


def decode(plain):
    """
    decode(str) -> str

    Decodes the given string using the atbash cipher into one block.
    """

    return plain.lower().translate(substitution, delete_chars)
