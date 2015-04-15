# Part 3: anagrams


# input:['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# combine_anagrams(['caRs', 'for', 'Potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter


# Strategy:  For each word, add it to a hash where the key is the sorted letters of the word and the value is an array.
# Remember that case of input words should be preserved in output

def combine_anagrams(words)
	# <YOUR CODE HERE>
	word_hash = Hash.new {|hash, key| hash[key] = []}
	
	words.each do |w|
		word_hash[w.downcase.scan(/\w/).sort.join] << w		# Pushes the given object on to the end of this array.
		# ERROR  Got an error for using sort().  Or course sort does not work on String. It does work on Array and Enumerable
	end
	
	word_hash.values
	
end

