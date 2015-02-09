def combine_anagrams(words)
	hash=Hash.new
	words.each do |word|
		sorted=word.downcase.split(//).sort.join
		printf sorted
		if hash.has_key? sorted
	     hash[sorted] = hash[sorted] << word
        else
		 newarray = []
	     hash[sorted] = newarray << word
        end
	end
	returnarray = []
	hash.each do |key,value|
		returnarray << value
	end
returnarray
end