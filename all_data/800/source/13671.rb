#!/usr/bin/env ruby1.9.1
def combine_anagrams(words)
	hash = Hash.new
	result = Array.new
	words.each do |w|
		sortedw = w.downcase.chars.sort.join
		hash.has_key?(sortedw) and hash[sortedw] << w  or hash[sortedw] = [w]
	end
	hash.each do |key,value|
		result << value
	end
	return result
end
p combine_anagrams(ARGV)
