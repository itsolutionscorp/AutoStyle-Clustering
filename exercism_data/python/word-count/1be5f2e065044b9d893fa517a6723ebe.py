import string

def remove_all(input, deletechars):
  return input.translate(None, deletechars)

class Phrase(object):  
  def __init__(self, phrase):
    self.phrase = phrase
    
  def word_count(self):
    wordlist = remove_all(self.phrase, string.punctuation).lower().split()
    return dict( (word, wordlist.count(word)) for word in wordlist )
    
  
