#!/usr/bin/ruby

def combine_anagrams(words)
	rvalue = Array.new()
	hash = Hash.new()
	words.each { |word|
		x = word.downcase.chars.sort.join
		if hash.has_key?(x) == false
			hash[x] = Array.new()
		end
		hash[x].push(word)
	}
	i=0
	hash.each { |y|
		rvalue.push(y[1])
	}
	return rvalue
end

