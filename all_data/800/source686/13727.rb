=begin
Given an array of strings, write a method that groups them into anagram groups and returns 
the array of groups.  Case doesn't matter in classifying string as anagrams (but case should be 
preserved in the output), and the order of the anagrams in the groups doesn't matter.
=end

def combine_anagrams(words)
	#create a hash of stacks, with sorted, downcase letters as keys and stacks of combinations of those letters as values
	anagramHash = Hash.new(Array.new)
	#iterate through array of strings; for each string, insert it into the stack of the corresponding key in anagramHash
	words.each do |word|
		if anagramHash[word.chars.sort.join.downcase].empty?
			anagramHash[word.chars.sort.join.downcase] = Array.new
		end
		anagramHash[word.chars.sort.join.downcase] << word
		puts word.chars.sort.join.downcase
	end
	#for each key in anagramHash, output the contents of the stack
	output = Array.new
	anagramHash.each_value { |value| output << value }
	return output
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
