def combine_anagrams(words)
	results = Hash.new([])
	words.each { |word| results[word.downcase.split(//).sort.join] += [word] }
	return results.values
end