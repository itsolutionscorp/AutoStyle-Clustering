def combine_anagrams(words)
	result = {}
	
	words.each do |word|
		key = word.downcase.split('').sort
	
		if result.has_key? key
			result[key] << word
		else
			result[key] = [word]
		end
	end
	
	answer = []
	
	result.each_value do |val|
		answer << val
	end
	
	answer
end

#puts combine_anagrams(['first', 'FIRTS', 'fitsr', 'second', "SENDCO"])