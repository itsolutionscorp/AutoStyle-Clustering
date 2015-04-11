__author__ = 'gdunn'

import string

# alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
# 'v', 'w', 'x', 'y', 'z']

alphabet = string.ascii_lowercase


def format_ct(ct):
    """
    add a space after every 5th character, unless it's the end of the string
    :rtype : str
    """
    ciphertext = []
    for indx, val in enumerate(ct, start=1):
        if indx % 5 == 0 and indx != len(ct):
            ciphertext.append(val)
            ciphertext.append(' ')
        else:
            ciphertext.append(val)
    return ''.join(ciphertext)


def cypher(i):
    return alphabet[::-1][alphabet.index(i)]


def prepare_text(txt):
    return txt.lower().replace(" ", "")


def encode(plaintext):
    """

    :param plaintext:
    :type plaintext: str

    # lowercase and remove spaces
    # map each char into the reversed alphabet a[::-1][a.index(plainchar)]
    """
    pt = prepare_text(plaintext)
    ct = []
    for i in pt:
        if i in alphabet:
            ct.append(cypher(i))
        elif i in string.punctuation:
            continue
        else:
            ct.append(i)

    return format_ct(ct)


def decode(ciphertext):
    pt = []
    assert isinstance(ciphertext, str)
    # remove spaces
    ct = prepare_text(ciphertext)
    for i in ct:
        pt.append(cypher(i))
    return ''.join(pt)
