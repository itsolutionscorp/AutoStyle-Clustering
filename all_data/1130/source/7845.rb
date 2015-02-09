def combine_anagrams(words)
	hash = {}
	
	words.each do |word|
		key = word.downcase.chars.sort.join
		
		if hash[key].nil?
			hash[key] = []
		end
		hash[key] << word
	end
	
	return hash.values
end

puts "#{combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect}"