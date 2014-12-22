def combine_anagrams(words)
  word_hash = Hash.new([])
  anagrams = Array.new
  words.each { |word| word_hash[word.downcase.chars.sort] += [word] }
  word_hash.each { |key, val| anagrams.push(word_hash[key]) }
  anagrams
end

