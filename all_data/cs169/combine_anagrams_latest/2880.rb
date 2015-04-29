
def combine_anagrams(words)
	result = []
	sortedMatchHash = {}

	# Create a hash where the key is the sorted word, and the values are the original words.
	# This allows us to easily group anagram words together since they have the same sorted words.
	words.each do |word|
		sortedWord = word.downcase.chars.sort.join
		sortedMatchHash[sortedWord] = [] if !sortedMatchHash[sortedWord]
		sortedMatchHash[sortedWord] << word
	end

	# Now all we need to do is put the values (arrays with the original words) into the result array
	sortedMatchHash.each{|key, value| result << value}

	result
end

# test = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# p combine_anagrams test
