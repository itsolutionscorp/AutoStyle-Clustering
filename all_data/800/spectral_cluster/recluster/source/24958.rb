def combine_anagrams(words)
	anagram = []
	words.each do |word|
		temp = []
		w = word.downcase.split('').sort
		temp << word
		words.each do |i|
			w2 = i.downcase.split('').sort
			if w == w2 && word != i 
				temp << i				
			end
		end
		anagram << temp.sort
	end
	anagram.uniq
end

puts (combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])).inspect