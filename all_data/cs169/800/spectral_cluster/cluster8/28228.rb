#!/usr/bin/env ruby

TEST_ANA = true
def combine_anagrams(words)
  output = []
	words.each do |word|
		sorted = word.downcase.split("").sort.join
		found = false
		
		output.each do |group| 
			# only the first word is enough to tell
			g_sorted = group[0].downcase.split("").sort.join
			if g_sorted == sorted
				group.push(word)
				found = true
			end
		end
		
		if !found
			output.push([word])
		end
	end
	output
end

if TEST_ANA
	# input: 
	combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
	#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
	# ["creams", "scream"]]
	# HINT: you can quickly tell if two words are anagrams by sorting their
	#  letters, keeping in mind that upper vs lowercase doesn't matter
end