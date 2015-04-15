from collections import Counter
import string


def _stripPunctuation(msg):
  exclude = set(string.punctuation)
  return ''.join(ch for ch in msg if ch not in exclude)


def word_count(phrase):
  phrase = _stripPunctuation(phrase)
  words = [w.lower() for w in phrase.split()]
  return Counter(words)
