def combine_anagrams(words)
	anagrams = Array.new
	words.each do |word|
		if anagrams.empty?
			anagrams << [word]
		else
			found = false
			anagrams. each do |list|
				if list[0].downcase.chars.sort.join == word.downcase.chars.sort.join
					list << word
					found = true
					break;
				end
			end
			anagrams << [word] if !found
		end
	end
	
	return anagrams
end


input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
answer = combine_anagrams(input)
p answer


input = ['cars', 'potatoes', 'sCreaM','rAcS', 'four','sCar', 'for', 'creams'] 
answer = combine_anagrams(input)
p answer
