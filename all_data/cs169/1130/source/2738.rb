def combine_anagrams(words)
	return words.group_by{ |a| a.downcase().split(//).sort().reduce("") { |a,s| a + s } }.values()
end

