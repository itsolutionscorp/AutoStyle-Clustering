def combine_anagrams(words)
  word_hash = Hash.new []
  words.each do |word|
	word_hash[word.downcase.chars.sort] += [word]
  end
  return word_hash.values
end

