#!/usr/bin/ruby
#require 'pp'
def combine_anagrams (words)
	reg_h = {}
	words.each do |word|
		reg = word.downcase.chars.sort.join
		if reg_h.key?(reg) 
			reg_h[reg].push(word) #if !reg_h[reg].include?(word)
		else
			reg_h[reg] = [word]
		end
	end
    reg_h.each_value.to_a
end
#pp combine_anagrams(
#['cars', 'for', 'potatoes', 'cars','tops','stops','stops','stop', 'Racs', 'four','scar', 'creams', 'scream']
#)
#pp combine_anagrams(
#['a', 'a', 'a', 'A','b','b']
#)
