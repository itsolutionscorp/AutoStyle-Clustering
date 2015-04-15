class Phrase:
  """
  A phrase
  """
  def __init__(self,phrase):
    """
    Removes non alphanumeric characters from the phrase,
    changes all cases to lower case and puts the words in a list
    """
    self.phrase=phrase.lower()
    self.phrase=filter(lambda x: x.islower() or x.isdigit() or x==" ",self.phrase)
  def word_count(self):
    """
    Adds all words and numbers to the dictionary 'wordlist' as keys
    and the number of occurences as values
    """
    wordlist={}
    for word in self.phrase.split():
      if word in wordlist.keys():
        wordlist[word]+=1
      else:
        wordlist[word]=1
    return wordlist


def main():
  """
  Test function
  """
  testPhrase=Phrase("go G$$o, GO")
  print testPhrase.word_count()

if __name__=="__main__":
  main()
