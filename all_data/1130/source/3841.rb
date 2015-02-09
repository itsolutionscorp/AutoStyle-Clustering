# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
	anagrams = find_anagrams( words )
	if anagrams.empty?
		result = []
	else
		newWords = words - anagrams
		result = [anagrams] + combine_anagrams(newWords)
	end
	
	return result
	
end

# returns true if s1 and s2 are anagrams
def are_anagrams?( s1, s2 )
	transformS1 = s1.downcase.split('').sort.join('')
	transformS2 = s2.downcase.split('').sort.join('')
	return transformS1 == transformS2
end

# Returns all the anagrams with the first word
def find_anagrams( words )
	if words.empty?
		result = []
	else
		result = []
		source = words[0]
		words.each do |w|
			if are_anagrams?( source, w )
				result << w
			end
		end
	end
	
	return result
end

#puts are_anagrams?( "rats", "Arts" )
#puts find_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] ).to_s
#puts combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] ).to_s