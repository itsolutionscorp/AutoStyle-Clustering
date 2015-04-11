import string

def word_count(phrase):

  # Remove whitespace and \n and toss into array
  words = phrase.strip().replace("\n", " ").split(" ")

  # Remove empty strings
  words = filter(None, words)

  # Initialize new dict for count of occurences
  count = {}
  for word in words:
    if word not in count:
      count[word] = 1
    else:
      count[word] += 1

  return count
