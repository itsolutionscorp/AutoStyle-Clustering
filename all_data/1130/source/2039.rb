def combine_anagrams(words)
	raise ArgumentError if words.nil?
	word_groups = words.group_by do |word|
		word.downcase.chars.sort.join
	end.values
	return word_groups
end
