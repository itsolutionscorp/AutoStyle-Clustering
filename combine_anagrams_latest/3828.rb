#!/usr/bin/env ruby

def combine_anagrams(words)
	ret = Hash.new
	
	words.each do |i|
		tmp = i.downcase.split('').sort.join('')
		if not ret.has_key?(tmp)
			ret[tmp] = []
		end
		ret[tmp].push(i)
	end
	ret.values
end