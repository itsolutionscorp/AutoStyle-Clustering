# Part 3: Anagrams

def combine_anagrams(words)

	groups = []
	skips = []

	for i in 0..words.length-1
		word1 = words[i].downcase.chars.sort.join
		matches = []
				
		if skips.include?(i) == FALSE
			matches << words[i]
			for j in i+1..words.length-1
				word2 = words[j].downcase.chars.sort.join
			
				if word1 == word2
					matches << words[j]
					skips << j
				end 
			end
		end
		
		if matches.length > 0
			groups << matches
		end
	end
						
	groups
	return groups
end

combine_anagrams(['cars', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream'])