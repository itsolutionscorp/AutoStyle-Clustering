#!/usr/bin/python
import string

class Phrase:
  def __init__(self, phrase):
    self.__phrase = phrase
    self.__counts = {}


  def __sanitize(self, phrase):
    phrase = phrase.translate(None, string.punctuation)
    phrase = phrase.lower()
    return phrase

  def word_count(self):
    phrase = self.__sanitize(self.__phrase)
    for word in phrase.split():
      self.__counts[word] = self.__counts.setdefault(word, 0) + 1
    return self.__counts
