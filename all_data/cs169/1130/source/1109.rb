def printn string
	print string, "\n"
end

test = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']

def combine_anagrams(words)
	counts = Hash.new([])
	words.each {|w| counts[w.downcase.chars.sort.join] += [w]}
	return counts.values
end

printn combine_anagrams(test)