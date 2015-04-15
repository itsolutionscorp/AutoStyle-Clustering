def combine_anagrams(words)

	results = Hash.new{ |hash,key| hash[key] = []}
	words.each do | word |
		results[word.downcase.chars.sort.join] << word
	end

	final = []
	
	results.each do |a,b| 
		final << b
	end
	return final
end

 # l = ['cars', 'Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

 # combine_anagrams(l)
