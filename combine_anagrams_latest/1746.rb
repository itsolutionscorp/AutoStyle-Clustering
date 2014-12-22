def combine_anagrams(words)
	score = {}
	return_val = []
	words.each do |w| 
		sorted = w.downcase.chars.sort.join
		if !score.has_key? sorted
			score[sorted] = []
		end
		score[sorted] << w
	end
	score.each do |key,val|
		return_val << val
	end
	return return_val
end

print combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']