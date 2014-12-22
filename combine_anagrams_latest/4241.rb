def combine_anagrams(words)
	return words.map {|x| 
		words.select{|y| 
			x.downcase.chars.sort.join == y.downcase.chars.sort.join
			}}.uniq
end