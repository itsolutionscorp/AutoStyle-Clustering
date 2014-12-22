def combine_anagrams(words)
	h = Hash.new
	
	words.each do |w|
	anagrams = w.downcase.split('').sort.join
	unless h.has_key?(anagrams)
		h[anagrams] = Array.new
		h[anagrams] << w
	else
		h[anagrams] << w 
	end
	end
	h.values
end

