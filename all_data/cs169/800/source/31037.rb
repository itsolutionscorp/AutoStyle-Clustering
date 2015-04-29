# Part 3

def combine_anagrams(words)
	# Create a hash
	hash = Hash.new([])

	# Iterate over words
	words.each do |word|
		sorted_word = word.downcase.chars.sort.join
		if hash.has_key? sorted_word 
			hash[sorted_word].push(word)
		else
			hash[sorted_word] = [word]
		end
	end
	return hash.values
end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])