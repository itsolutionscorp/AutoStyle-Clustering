from string import ascii_lowercase, digits

_whitelist = ascii_lowercase + digits
_replacements = ''.join(reversed(ascii_lowercase)) + digits
_translator = str.maketrans(_whitelist, _replacements)

_whitelist_set = set(_whitelist)


def _filter_and_translate(s):
    s = ''.join(c for c in s.lower() if c in _whitelist_set)
    return s.translate(_translator)


def encode(s):
    s = _filter_and_translate(s)
    return ' '.join(s[i:i+5] for i in range(0, len(s), 5))


def decode(s):
    return _filter_and_translate(s)
