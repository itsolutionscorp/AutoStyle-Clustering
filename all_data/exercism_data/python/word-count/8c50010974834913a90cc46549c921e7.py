import re

def word_count(message):
  # Returns a count of the number of each word in a message.
  # My first thought is that a hash would work, though it might not be
  # as efficient as other methods. It would, however, be very readable.

  count = {}

  for word in message.split():
    word = word.lower(); # Convert to lower case
    word = re.sub(r'\W+', '', word); # Get rid of non-alphabetical characters
    if(word != ''): # We might be left with a null word
      if (word in count):
        count[word] = count[word.lower()] + 1;
      else:
        count[word] = 1;

  return count
