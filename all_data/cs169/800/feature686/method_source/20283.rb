def combine_anagrams(words)
  anagrams = {}
  anagrams.default = []
  words.each do |word|
    key = word.downcase.chars.sort.join
    anagrams[key] = (anagrams[key] + [word])
  end
  anagrams.values
end