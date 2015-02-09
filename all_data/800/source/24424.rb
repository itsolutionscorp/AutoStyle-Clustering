#!/usr/bin/env ruby


def combine_anagrams(words)

	out = []

	words.each do |word|
		didmatch = false
		out.each do |compare|
			if compare[0] and compare[0].downcase.split(//).sort == word.downcase.split(//).sort
				compare.push(word)
				didmatch = true
			end
		end
		if not didmatch
			out.push([word])
		end
	end

	return out

end

#print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
#puts

print combine_anagrams(["hello","HEllO"])
