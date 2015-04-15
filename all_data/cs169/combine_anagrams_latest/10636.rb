def combine_anagrams(words)
	h = Hash.new
	words.each do |word|
		key = word.downcase.scan(/./).sort.to_s
		if h.has_key?(key)
			h[key] = h[key] +[ word]
		else
			h[key]=[word]
		end
	end
	return h.values

end


if __FILE__ == $0
#combine_anagrams(
#['CARS', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'a', 'A', 'a', 'a', 'Aaaa', 'Aa'] ).each {|x| puts '-----------------Group', x}
p combine_anagrams(
['CARS', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'a', 'A', 'a', 'a', 'Aaaa', 'Aa'] )
#"cars".downcase.scan(/./) {|x| print "<<#{x}>>"}
#print "cars".downcase.scan(/./).sort.to_s
end
