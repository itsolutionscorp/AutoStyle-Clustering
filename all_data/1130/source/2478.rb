def combine_anagrams(words)
	anagrams = Array.new
	comp = words.collect{|w| w.downcase.chars.sort.join}.uniq
	comp.each {|w|
		tmp = words.select{|word| word if word.downcase.chars.sort.join == w}
		anagrams.push(tmp)
	}
	return anagrams
end

#a = combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
#print a, "\n"

