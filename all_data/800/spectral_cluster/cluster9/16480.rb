# By Henrik Torkelund, torkelund@gmail.com, 2012.
# Software as a Service course
# Homework 1, 3: anagrams

# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)	
	anagrams = Hash.new()	
	words.length.times do |i|
		hash_value = 0
		#sort the characters in the word
		word = words[i].split('').sort!.join('')
		word.length.times do |j|#calculate hash
			#convert to lower case before calculating hash
			hash_value+=word.clone.slice!(j).downcase.ord			
		end		
		if anagrams.has_key?(hash_value) then			
			anagrams[hash_value] << words[i]
		else			
			anagrams[hash_value] = Array.new()
			anagrams[hash_value] << words[i]
		end	
	end	
	anagrams.values
end

p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])