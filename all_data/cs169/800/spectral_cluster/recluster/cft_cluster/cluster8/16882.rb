# HW 1 Part 3: Anagrams

def combine_anagrams(words)
  sortedchars = []
  words.each { |word| sortedchars << word.downcase.split(//).sort }
  h = {}
  sortedchars.uniq.each { |chars| h[chars] = [] }
  words.each { |word| h[word.downcase.split(//).sort] << word }
  h.values
end