#!/usr/bin/env ruby

def combine_anagrams(words)
	h = Hash.new()
	words.each do |word|
		if h[formatted(word)]
			h[formatted(word)] = h[formatted(word)] << word
		else
			h[formatted(word)] = [word]
		end
	end
	result = Array.new(h.size)
	h.keys.each_with_index do |key, index|
		puts key
		result[index] = h[key]
	end
	return result	 	
end

def formatted(string)
	return string.downcase.chars.sort.join
end

def anagram?(string1, string2)
	if string1.formatted.eql?(string2.formatted)
		return true
	end
	return false
end

if __FILE__a=$0
	p combine_anagrams(["cars","for","potatoes","racs","four","scar","creams","scream"])
	p combine_anagrams(["cars","Csar","p"])
end
