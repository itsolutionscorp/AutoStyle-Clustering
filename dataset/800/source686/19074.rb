
def combine_anagrams(words)

	groups  = Hash.new
	output = Array.new()
	words.each { |word|
		hash= word.downcase.chars.sort.join
		if (groups[hash] == nil)
			groups[hash] = Array.new()
		end
		groups[hash] << word		# adding value to the array
	}
	# loop into the hash to create the output array 
	groups.each { |key, value|
		output << value	
	}
	print "The grouping is: "
	print output
	print "\n"
	return output  
end


# input: 
array = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
combine_anagrams array
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

array = ['a','A'] 
combine_anagrams array