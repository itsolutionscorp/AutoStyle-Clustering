def combine_anagrams(words)
	anagrams = Hash.new{|h, k| h[k] = []}
	words.each do |word|
		anagrams[word.downcase.chars.sort.join] << word
	end
	return anagrams.values
end