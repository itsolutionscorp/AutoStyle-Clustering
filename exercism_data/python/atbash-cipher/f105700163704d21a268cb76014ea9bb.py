# -*- coding: utf-8 -*-

sigma_alpha = "abcdefghijklmnopqrstuvwxyz"
sigma_num = "0123456789"

substitution = dict(zip(sigma_alpha, reversed(sigma_alpha))
                    + zip(sigma_num, sigma_num)
                    )


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

    plain = plain.lower()
    cipher = "".join([substitution.get(p,"") for p in plain])

    return cipher
