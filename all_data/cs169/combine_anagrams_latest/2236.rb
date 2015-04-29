def combine_anagrams(words) 
	h = Hash.new
	words.each { |w| 
		sorted = w.downcase.split(//).sort.join
		if (h.has_key?(sorted)) then h[sorted]<<w
		else h[sorted]=[w]
		end
	}
	h.values
end

#t = ['cars', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream']
#p combine_anagrams( t )
