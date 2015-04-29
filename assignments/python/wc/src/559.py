import re
def word_count(message):
  count = {}
  for word in message.split():
    word = word.lower(); # Convert to lower case
    word = re.sub(r'\W+', '', word); # Get rid of non-alphabetical characters
    if(word != ''): # We might be left with a null word
      if (word in count):
        count[word] = count[word] + 1;
      else:
        count[word] = 1;
  return count
