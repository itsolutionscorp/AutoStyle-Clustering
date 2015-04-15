import string

class Phrase(object):
  def __init__(self,sentence):
    self.sentence = sentence

  def word_count(self):
    list_words = dict()
    sentence_cleaned = self.sentence.translate(string.maketrans("",""), string.punctuation).lower()
    for word in sentence_cleaned.split():
      if word.isalpha() or word.isdigit():
	if word in list_words:
	  list_words[word] += 1
	else:
	  list_words[word] = 1
    return list_words
