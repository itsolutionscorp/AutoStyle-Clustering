import re
import string

invalid_chars = re.compile(r"[^a-z0-9]")

valid_letters = "".join(chr(c) for c in xrange(ord("a"), ord("z") + 1))
translation = string.maketrans(valid_letters, "".join(reversed(valid_letters)))

def translate(s):
    return string.translate(s, translation)

def encode(s):
    normalized = invalid_chars.sub("", s.lower())
    encoded = translate(normalized)
    return " ".join( encoded[i:i+5] for i in xrange(0, len(encoded), 5) )

def decode(s):
    spaces_stripped = "".join(s.split())
    return translate(spaces_stripped)
