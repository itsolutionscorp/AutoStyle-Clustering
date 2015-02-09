def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    key = word.downcase.split("").sort.join
    anagrams[key] ||= []
    (anagrams[key] << word)
  end
  anagrams.values
end

