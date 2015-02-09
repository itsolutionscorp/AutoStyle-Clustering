def combine_anagrams(words)
	words.map do |w| 
		w.downcase.chars.sort
	end.uniq.map do |s|
		words.select { |w| w.downcase.chars.sort == s }
	end
end
