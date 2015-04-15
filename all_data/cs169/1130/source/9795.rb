def anagrams?(w1, w2)
  w1.downcase.split('').sort == w2.downcase.split('').sort
end

def combine_anagrams(words)
	result = []
	words.each do |word|
		if result.empty?
			result.push [word] 
		else
			found = false
			result.each do |r|
				if anagrams?(r[0], word)
					r.push word
					found = true
					break
				end
			end
			result.push [word] unless found
		end
	end
	result
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s

# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter