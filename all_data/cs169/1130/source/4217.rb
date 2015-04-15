#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'a', 'A', 'hello', 'HeLlo']

def combine_anagrams(words)
  anagrams = {}
  words.each {|word| anagrams = anagrams.merge(word.downcase.chars.sort.join => anagrams.fetch(word.downcase.chars.sort.join,[]) << word)}
  anagrams.values
end