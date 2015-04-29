#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import re
from textwrap import wrap

# Submission file for the python gigasecond_test exercise.
#
# v1: Using regular expressions for normalising, a translation for
#     encoding, and textwrap.wrap() for chunking.


pattern = re.compile(r'[^a-z0-9]')
encoder = str.maketrans('abcdefghijklmnopqrstuvwxyz',
                        'zyxwvutsrqponmlkjihgfedcba')


def encode(plaintext):
    """
    Encode an plaintext string using a simple Atbash cipher.

    The Atbash cipher is a simple substitution cipher that relies on
    transposing all the letters in the alphabet such that the resulting
    alphabet is backwards. The first letter is replaced with the last
    letter, the second with the second-last, and so on.

    Ciphertext is written out in groups of 5 letters, and punctuation is
    excluded. This is to make it harder to guess things based on word
    boundaries.
    """

    normalised = re.sub(pattern, '', plaintext.lower())
    normalised = " ".join(wrap(normalised, 5))

    return normalised.translate(encoder)


def decode(ciphertext):
    """
    Decode an Atbash-encoded ciphertext and return the plaintext.

    The Atbash cipher is a simple substitution cipher that relies on
    transposing all the letters in the alphabet such that the resulting
    alphabet is backwards. The first letter is replaced with the last
    letter, the second with the second-last, and so on.

    Since original word boundaries are not known at this stage, the
    plaintext is stripped of any white-space characters, and the plain-
    text is returned as one coherent string.
    """

    return re.sub(pattern, '', encode(ciphertext))
