def combine_anagrams(words)
	combined = Hash.new
	words.each do |x|
		sortedWord = x.downcase.split(//).sort.to_s
		if combined[sortedWord]	# existing anagram
			combined[sortedWord] += [x]
		else  # new anagram
			combined[sortedWord] = [x]
		end
	end
	return combined.values
end
