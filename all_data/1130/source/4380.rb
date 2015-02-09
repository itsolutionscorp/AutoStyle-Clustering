def combine_anagrams(words)
	coll = {}
	words.each do |word|
		key = word.downcase.chars.sort.join
		if coll[key] == nil
			coll[key] = [word]
		else
			coll[key] << word
		end
	end
	result = []
	coll.each_pair do |k, v|
		result << v
	end
	return result
end