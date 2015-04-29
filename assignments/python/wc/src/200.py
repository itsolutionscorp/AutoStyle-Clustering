from __future__ import print_function
import re
def word_count(message):
  count = {}
  for word in message.split():
    word = word.lower(); # Convert to lower case
    word = re.sub(r'\W+', '', word); # Get rid of non-alphabetical characters
    if(word != ''): # We might be left with a null word
      if (word.lower() in count):
        count[word.lower()] = count[word.lower()] + 1;
      else:
        count[word.lower()] = 1;
  return count
