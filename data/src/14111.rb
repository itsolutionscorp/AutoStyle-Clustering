#!/usr/bin/ruby

def combine_anagrams(words)
	result = []
	words.each do |word|
		match = false
		result.each do |rs|
			if rs[0].downcase.split('').sort==word.downcase.split('').sort
				rs << word
				match = true
			end
		end
		arr = []; arr << word
		unless match ; result << arr ; end
	end
	return result
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])
