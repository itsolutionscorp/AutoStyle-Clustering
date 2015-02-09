
=begin

An anagram is a word obtained by rearranging the letters of another word. For example, "rats", "tars" and "star" are an anagram group because they are made up of the same letters.
Given an array of strings, write a method that groups them into anagram groups and returns the array of groups. Case doesn't matter in classifying string as anagrams (but case should be preserved in the output), and the order of the anagrams in the groups doesn't matter.

Example:
# input:
['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their # letters, keeping in mind that upper vs lowercase doesn't matter

=end

def get_key(word)
	return word.downcase.chars.sort.join
end

def combine_anagrams(words)
	h = Hash.new(0)
	words.each do |word|
		puts("Processing #{word}")
		key = get_key(word)
		puts("Key=#{key}")
		if h.has_key? key
			puts("exists")
			h[key].push word
		else
			puts("non existent")
			a = Array.new
			a.push word
			h[key] = a
		end
	end
	
	b = Array.new
	h.each_value do |value|
		b.push value
	end
	
	return b
end

=begin
b = combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

puts
puts "----------------"
puts b
puts "----------------"
puts

b = combine_anagrams ['carS', 'for', 'potatoes', 'rAcs', 'four','Scar', 'Creams', 'Scream', 'Atoptoes']

puts
puts "----------------"
puts b

=end
