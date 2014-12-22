def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    anagrams[key] = [] unless anagrams.has_key?(key)
    (anagrams[key] << word)
  end
  anagrams.values
end

