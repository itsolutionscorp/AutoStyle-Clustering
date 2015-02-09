def combine_anagrams(words)
	anahash = Hash.new
	words.each do |word|
		key = word.downcase.split.sort
		if anahash.has_key? key then
			unless anahash[key].include? word
				anahash[key].concat [word]
			end
		else anahash[key] = [word]
		end
	end
	return anahash.values
end