class Anagram:

  def __init__(self, target):
    self.__target = self.__anagram_key(target)

  def match(self, words):
    return reduce(self.__add_anagram, words, [])

  def __add_anagram(self, matches, word):
    if self.__target == self.__anagram_key(word):
      matches.append(word)
    return matches

  def __is_anagram(self, word):
    return self.__target == self.__anagram_key(word)

  def __anagram_key(self, word):
    key = list(word.lower())
    key.sort()
    return key
