class Anagram(object):
  def __init__(self, base):
    self.base = base

  def match(self, word_list):
    output = []
    for word in word_list:
      if word != self.base:
        if sort_word(self.base) == sort_word(word):
          output.append(word)
    return output
  
def sort_word(word):
  letters = list(word.lower())
  letters.sort()
  return "".join(letters)
