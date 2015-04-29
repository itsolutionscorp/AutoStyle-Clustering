def combine_anagrams(words)
	groups = {}
	words.each do |word|
		key = word.downcase.chars.sort.join
		if not groups[key]
			groups[key] = [word]
		else
			groups[key] << word
		end
	end
	groups.values
end
