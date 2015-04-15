#!/usr/local/bin/ruby

# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	annagrams = Hash.new;
	words.each do |word|
		sortedWord = word.length > 1 ? word.downcase.chars.sort.join : word.downcase
		if annagrams.key?(sortedWord)
			annagrams[sortedWord].push(word)
		else
			annagrams[sortedWord] = Array(word)
		end
	end
	return annagrams.values;
end

#input = ['a', 'A', 'cars', 'for', "HeLLo", "hello", 'potatoes', 'racs', 'four','scar', 'creams', 'scream'];
#output = combine_anagrams(input)
#expectedOutput = [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]];
#raise 'did not match' unless ((output | expectedOutput) - (output & expectedOutput)).length == 0;
