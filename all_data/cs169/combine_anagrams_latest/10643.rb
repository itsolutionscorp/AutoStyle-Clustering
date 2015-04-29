def combine_anagrams(words)
	hash = Hash.new;
	words.each { |word|
		key = word.downcase.chars.sort.join;
		hash[key] = Array.new unless hash.has_key? key
		hash[key] << word
	}

	# Here we have a hash (we needed it to recognize words as anagrams); return the values
	hash.values
end


#################### Test code 

def print_anagrams(anagrams)
	anagrams.each { |same| 
		same.each { |word| print word + ' ' }
		print "\n"
	}
end

print_anagrams combine_anagrams(
	['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
)
