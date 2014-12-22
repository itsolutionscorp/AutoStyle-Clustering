
def combine_anagrams(words)
	hash = {}
	words.each do |wrd|
		wrd_srt = sort_word(wrd.downcase)
		array = hash [wrd_srt]
		if array == nil
			array = []
		end
		array << wrd
		hash[wrd_srt] = array
	end
	return hash.values
end

def sort_word(wrd)
	wrd_array = wrd.split(//).sort
	new_wrd = ''	
	wrd_array.each do |l|
		new_wrd << l
	end
	return new_wrd
end

result = combine_anagrams (['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts result.to_s



