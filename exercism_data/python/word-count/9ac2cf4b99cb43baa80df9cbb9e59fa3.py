def word_count(string):
  frequencies = {}

  for word in string.split():
    if word in frequencies:
      frequencies[word] += 1

    else:
      frequencies[word] = 1

  return frequencies
