def combine_anagrams(words)
	anagrama = Array.new
	words.each do |m|		
		ar = words.select { |n| m.downcase.chars.sort.join == n.downcase.chars.sort.join}
		if not anagrama.include?(ar)
			anagrama.push(ar)
		end
	end
	return anagrama
end