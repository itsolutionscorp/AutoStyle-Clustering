def combine_anagrams(words)
  words_hash = Hash.new([])
  words.each { |word| words_hash[word.downcase.chars.sort] += [word] }
  words_array = []
  words_hash.each { |key, value| (words_array << value) }
  words_array
end

