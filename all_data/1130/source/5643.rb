def combine_anagrams (list)
	anagrams = {}
	list.each do |element|
		sorted_letters = element.downcase.chars.sort
		if anagrams.has_key?(sorted_letters)
			anagrams[sorted_letters] << element
		else
			anagrams[sorted_letters] = [element]
		end
	end
	return anagrams.values
end
