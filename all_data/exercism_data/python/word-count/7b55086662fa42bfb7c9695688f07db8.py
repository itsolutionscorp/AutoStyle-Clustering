from collections import Counter

def word_count(phrase):
  # The awesome Counter class which was build exactly for this type of task
  # https://docs.python.org/2/library/collections.html?highlight=counter#collections.Counter
  word_map = Counter()

  for word in phrase.split():
    # Insert into the map and/or increment subsequent encounters of the same word
    word_map[word] += 1

  # Counter is sublcass of dict, so this should be safe. Could also call .most_common()
  return word_map
