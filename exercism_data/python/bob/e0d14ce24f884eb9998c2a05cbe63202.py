from __future__ import unicode_literals
from six import text_type

def hey(s):
    s = text_type(s).strip()
    criteria = [
        lambda s: 'Fine. Be that way!'  if not s else None,
        lambda s: 'Woah, chill out!'    if s.isupper() else None,
        lambda s: 'Sure.'               if s.endswith('?') else None,
        lambda s: 'Whatever.'
    ]

    for c in criteria:
        rv = c(s)
        if rv:
            return rv
