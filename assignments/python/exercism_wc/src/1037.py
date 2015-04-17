import re
def word_count(text):
    """Examine a string and return a dictionary of all words used 
       with the number of occurences of each word
    """
    words = {}
    wordPattern = re.compile("[\S]+", re.IGNORECASE)
    for m in wordPattern.finditer(text):
      word = m.group(0)
      if word in words:
         words[word] += 1
      else:
          words[word] = 1
    return words