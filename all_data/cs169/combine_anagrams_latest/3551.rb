def combine_anagrams(words)
	groups = Hash.new
	words.each do |word|
		sorted = word.downcase.split("").sort.join
		if groups.has_key? sorted
			groups[sorted] << word
		else
			groups[sorted] = Array[word]
		end 
	end
	groups.values
end
