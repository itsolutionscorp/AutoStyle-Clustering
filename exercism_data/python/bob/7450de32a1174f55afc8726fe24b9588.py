import regex
from string import punctuation

def hey(what):
    what = what.strip()
    plain = regex.sub(u'[\s{}]+'.format(regex.escape(punctuation)), '', what)

    if not regex.match(u'^[\d\s]+$', plain):
      if regex.match(u'^\s*$', what):
        return 'Fine. Be that way!'
      if regex.match(u'^[\d\s\p{Lu}]+$', plain):
        return 'Whoa, chill out!'
    if what.endswith('?'):
      return 'Sure.'
    return 'Whatever.'
