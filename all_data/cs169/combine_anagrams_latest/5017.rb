# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	
	result = []
	words1 = [];
	words.each {|w| words1  << w.to_s.downcase.chars.sort.join}
	words2 = words1.uniq
	
	for i in 0 ... words2.size
		r = []
		w2 = words2[i]
		for j in 0 ... words.size
			w = words[j]
			if w2.casecmp(w.to_s.downcase.chars.sort.join) == 0
				r << w
			end
		end
		result << r
	end
	
	return result
	
end

# input:
words = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]

puts combine_anagrams(words)