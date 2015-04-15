# input:  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
	result = Hash.new;
	words.each do |word|
		if result.has_key? word.downcase.chars.sort.join
			result[word.downcase.chars.sort.join] += [word]
		else
			result[word.downcase.chars.sort.join] = [word]
		end
	end
	return result.values
end