def combine_anagrams(words)

	words_already_selected = ""
	result = []

	words.each 	{ |w|

		if words_already_selected.include?(w)
			next
		end

		group = words.select {|x| x.downcase.chars.sort.join.eql? w.downcase.chars.sort.join}
	
		words_already_selected += group.join("|")

		result << group
	}

	return result
end