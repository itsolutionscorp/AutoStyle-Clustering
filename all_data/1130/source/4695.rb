def combine_anagrams(words)
	words.each.inject({}) do |groups, word|
		(groups[word.downcase.chars.sort.join] ||= []) << word
		groups
	end.values
end
