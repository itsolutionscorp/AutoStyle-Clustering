def combine_anagrams(words)
	anagrams = Array.new()
	words.each do |word|
		inserted = false
		anagrams.each do |set|
			set.each do |setword|
				if setword.downcase.chars.sort.join == word.downcase.chars.sort.join
					set.push word
					inserted = true
				break
				end
			end
			if inserted 
				break			
			end
		end
		if !inserted
			new_set = Array.new
			new_set.push word
			anagrams.push new_set
		end

	end
p anagrams
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )

