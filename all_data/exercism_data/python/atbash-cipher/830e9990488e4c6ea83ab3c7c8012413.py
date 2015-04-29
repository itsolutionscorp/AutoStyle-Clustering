__author__ = 'Cedric Zhuang'

from string import maketrans
import re

alphabet = "abcdefghijklmnopqrstuvwxyz"

encode_trans = maketrans(alphabet, alphabet[::-1])
decode_trans = maketrans(alphabet[::-1], alphabet)


def decode(s):
    s = re.sub('\s', '', s)
    return s.translate(decode_trans)


def encode(s):
    s = s.lower()
    s = re.sub('[\s\.,]', '', s)
    translated = s.translate(encode_trans)
    return re.sub('(.{5})', '\g<0> ', translated).strip()
