import string

def removeAll(str, deletechars):
  return str.translate(None, deletechars)

class Phrase(object):  
  def __init__(self, phrase):
    self.phrase = phrase
    
  def word_count(self):
    wordlist = removeAll(self.phrase, string.punctuation).lower().split()
    return dict([ [word, wordlist.count(word)] for word in wordlist ])
    
  
