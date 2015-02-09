
public
def decode(stringin)
	return stringin.downcase.chars.sort.join
end


##############################
def combine_anagrams(words)
	decoded_array = []
	result = []
	arrays_hashed = {}
	
	words.each { |word|
		if arrays_hashed.has_key?(decode(word))
			arrays_hashed.store(decode(word), arrays_hashed.fetch(decode(word)).push(word))
		else
			arrays_hashed.store(decode(word), [word])
		end
	}
	
	arrays_hashed.each_value {|value| result.push(value)}
	
	return result
end




