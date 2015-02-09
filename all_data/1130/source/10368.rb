# input: 
a=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	h = Hash.new
	words.each do |a_word|
		key = a_word.downcase.chars.sort.join
		puts key
		puts "-------"
		if h[key] == nil
			h[key] = Array.new
		end
		h[key].push(a_word)
	end
	
	r_a=Array.new
	
	h.each do |key, list|
	r_a.push(list)
	end
	return r_a
end

#puts combine_anagrams (a)