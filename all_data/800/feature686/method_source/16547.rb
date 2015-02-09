def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    key = word.split(/\s*/).sort.join
    anagrams[key] ||= []
    (anagrams[key] << word)
  end
  anagrams.values
end