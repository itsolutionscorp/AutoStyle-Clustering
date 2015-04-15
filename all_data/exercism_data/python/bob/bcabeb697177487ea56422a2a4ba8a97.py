# -*- coding: utf-8 -*-

import string
import unicodedata


def _remove_accents(data):
    nkfd_form = unicodedata.normalize('NFKD', data)
    return u"".join([c for c in nkfd_form if not unicodedata.combining(c)])


def _is_shouting(data):
  data_letters = [c for c in data if c in string.ascii_letters]
  if len(data_letters) > 0:
    return all(map(
        lambda c: c in string.ascii_uppercase,
        data_letters))
  else:
    return False


def _is_question(data):
  return data[-1] == '?'


BOB_RULES = [
  ('Fine. Be that way!', lambda d: d == ''),
  ('Whoa, chill out!', _is_shouting),
  ('Sure.', _is_question),
]


def hey(data):
  data = data.strip(' \t\n\r')
  data = _remove_accents(data)
  for response, condition in BOB_RULES:
    if condition(data):
      return response
  return 'Whatever.'
