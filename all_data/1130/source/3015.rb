
def combine_anagrams(words)
  anagrams = {}
  words.collect{ |word|
    sorted = word.downcase.split(//).sort.join
    anagrams[sorted].nil? ? anagrams[sorted] = [word] : anagrams[sorted] << word
  }
  anagrams.values
end

