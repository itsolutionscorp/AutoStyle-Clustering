require 'pp'

def combine_anagrams (words)
	anagrams = []
	words.group_by { |w| w.downcase.chars.sort.join }.each do |k,v|
		anagrams.push(v)
	end

	anagrams
end

##pp combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])