class Anagram:

  def __init__(self, target):
    self.__target = self.__anagram_key(target)

  def match(self, words):
    return [word for word in words if self.__is_anagram(word)]

  def __is_anagram(self, word):
    return self.__target == self.__anagram_key(word)

  def __anagram_key(self, word):
    return sorted(word.lower())
