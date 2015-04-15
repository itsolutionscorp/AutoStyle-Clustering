#require 'pp'

def combine_anagrams(words)	
	anagrams = Hash.new()	
	words.length.times do |i|
		hash_value = 0
		#sort the characters in the word
		word = words[i].downcase.split('').sort!.join('')
		word.length.times do |j|#calculate hash
			#convert to lower case before calculating hash
			hash_value+=word.clone.slice!(j).ord			
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
#PP.pp(combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']), $>, 40)


# Output
#[["scar", "cars", "racs", "scream", "creams"], ["for", "four", "potatoes"]]
