#Given an array of strings, write a method that groups them into anagram groups and returns
#the array of groups. Case doesn't matter in classifying string as anagrams (but case should be
#preserved in the output), and the order of the anagrams in the groups doesn't matter.

def combine_anagrams(words)
	[] unless words.empty?
	combine = Hash.new
	words.each do |word|
		sorted = word.downcase.chars.sort.join
		if combine.has_key?(sorted)
			combine[sorted] << word
		else
			combine[sorted] = [word]
		end	
	end
	combine.values
end


#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#puts combine_anagrams(words)
