def combine_anagrams(words)
	temp={}
	words.each do |elt|
		spell = elt.downcase.split('').sort.join
		temp[spell] ||= []
		temp[spell] << elt
	end
	return temp.values
end

combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])