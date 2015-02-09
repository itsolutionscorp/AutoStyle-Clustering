def combine_anagrams(words)
	anagrams = []
	while words.length > 0
		group = [words.delete_at(0)]
		if words.length > 0
			words = words.reject{ |word|
				if group[0].downcase.chars.sort == word.downcase.chars.sort
					group << word
					true
				else
					false
				end
			}
		end
		anagrams << group
	end
	anagrams
end