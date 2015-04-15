def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    key = word.split("").sort.join
    anagrams[key] ||= []
    (anagrams[key] << word)
  end
  return anagrams.values
end