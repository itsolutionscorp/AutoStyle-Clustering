def are_anagrams? ( a, b)
	a.downcase.chars.sort.join == b.downcase.chars.sort.join
end

def create_anagram_hash (words)
	hash = Hash.new
	
	words.map do
		|word|

		added = false

		hash.keys.each do
			|key|
			if are_anagrams?(word, key)
				hash[key]  << word
				added = true
				break
			end
		end
		
		unless added
			hash[word] =  [word]
		end
		
	
	end
	hash	
end

def combine_anagrams(words)
	
	array = []
	create_anagram_hash(words).each_pair do
		|key, value |
		array << value
	end

	array
end	

=begin	
print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])	
=end	
	
