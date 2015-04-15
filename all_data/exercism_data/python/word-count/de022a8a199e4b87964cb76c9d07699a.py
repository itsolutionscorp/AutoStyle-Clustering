def word_count(phrase):
  return { i : phrase.count(i) for i in phrase.split(" ") }
