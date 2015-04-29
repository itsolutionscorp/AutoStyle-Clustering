def word_count(phrase):
  response = {}
  for word in phrase.split():
    response[word] = response.get(word,0) + 1
  return response
