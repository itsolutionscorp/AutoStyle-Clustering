=begin
Part 3: anagrams
An anagram is a word obtained by rearranging the letters of another word. For
example, "rats", "tars" and "star" are an anagram group because they are made up of the same
letters.
Given an array of strings, write a method that groups them into anagram groups and returns
the array of groups. Case doesn't matter in classifying string as anagrams (but case should be
preserved in the output), and the order of the anagrams in the groups doesn't matter.
Example:
# input:
['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
=end
def combine_anagrams(words)
	values ||=[]
	words.group_by{ |word| word.downcase.chars.sort.join}.each{|k,v| values << v}
	values
end

p combine_anagrams(['caRs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])