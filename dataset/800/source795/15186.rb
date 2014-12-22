# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
	hash = {}
	words.each do |x| 
	index = x.downcase.split(//).sort
	val = hash[index]
		if val
			hash[index] = val + Array[x]
		else
			hash[index] = Array[x]
		end
	end
	return hash.values
end

input = ['Cars', 'for', 'Potatoes', 'racs', 'four','scar', 'creams','scream']
puts combine_anagrams(input).inspect