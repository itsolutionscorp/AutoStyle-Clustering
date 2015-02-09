words = ['cars', 'For', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
def combine_anagrams(words)
	groups = []
	words.each do |word|
		if groups.length == 0
			groups[0] = [word]
		else
			found = false
			groups.each_index do |index|
				if groups[index][0].downcase.split("").sort == word.downcase.split("").sort
					groups[index] << word
					found = true
					break if found == true
				end	
			end
			if found == false
				groups << [word]
			end
		end
 	end
	return groups
end
puts combine_anagrams(words)
