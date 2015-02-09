def combine_anagrams(words)
	r = {}

	words.each do |w|
		k = w.downcase().split(//).sort().join("")
		if r[k] == nil
			r[k] = [ w ]
		else
			r[k] = r[k] << w
		end
	end

	return r.values
end


#print ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'].inspect
#print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
