def combine_anagrams(words)
	hash = {}
	words.each do |w|
		if hash[w.downcase.split(//).sort] == nil then
			hash[w.downcase.split(//).sort] = [w]
		else
			hash[w.downcase.split(//).sort] << w	
		end	
	end
	hash.values
end

def is_anagram w1, w2
	w1.downcase.split(//).sort == w2.downcase.split(//).sort
end	

def test
	w =['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
	combine_anagrams w
end

