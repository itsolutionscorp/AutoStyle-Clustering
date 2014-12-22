#
# An anagram is a word obtained by rearranging the letters of 
# another word. For example, "rats", "tars" and "star" are an 
# anagram group because they are made up of the same letters.
#
# Given an array of strings, write a method that:
# 	-- groups them into anagram groups and 
# 	-- returns the array of groups. 
# 	-- Case doesn't matter in classifying string as anagrams 
# 		(but case should be preserved in the output), and 
# 	-- the order of the anagrams in the groups doesn't matter.
#
# Example:
# 	# input:
# 	['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# 	# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# 	["creams", "scream"]]
# 	# HINT: you can quickly tell if two words are anagrams by sorting their
# 	# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
	newlist = words.map {|each_word| each_word.split(//).
												sort.
												join.
												downcase}
	h = {}
	newlist.each_index do |index| 
		unless h.has_key?(newlist[index])
			h[newlist[index]] = []
		end
		h[newlist[index]] << words[index]
	end
	anagramlist = []
	h.each { |key, val| anagramlist << h[key] }
	return anagramlist
end

# unit tests:
# words = ["one", "Eno", "seat", "teas", "sate", "ate", "tea", "car", "arc", "rob"]
# combine_anagrams(words).each {|group| puts "Ex:"; group.each {|word| puts "\t#{word}"}}
#
# puts "\nGiven example:"
# words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# combine_anagrams(words).each {|group| puts "Ex:"; group.each {|word| puts "\t#{word}"}}
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# 													["creams", "scream"]]
# 
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
