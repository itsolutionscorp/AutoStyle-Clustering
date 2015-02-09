def combine_anagrams(words)
	h = {}

	words.each do |s| 
		key = s.downcase.chars.sort.join
		if(h[key])
      		h[key].push(s)
   		else
      		h[key] = [s]
   		end			
	end

	h.values
end