def combine_anagrams(words)

	hash_grouper = Hash.new
	
	for i in 0..words.length-1 do

		key=words[i].downcase.split(//).sort.to_s
		if (hash_grouper[key] == nil)
			hash_grouper[key] = [words[i]]
		else
			hash_grouper[key].push(words[i])		
		end
	
	end
	
	# put hash content in a list
	list_of_words = Array.new(0)
	hash_grouper.each {|key, value| list_of_words.push(value)}
	
	return list_of_words
	
end

