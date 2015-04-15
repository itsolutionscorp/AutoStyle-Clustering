
def combine_anagrams(words)
  groups = []
  
  words.each do |word|
	@grouped = false
  
	groups.each do |subGroup|
		if (word.downcase.chars.sort.join == subGroup[0].downcase.chars.sort.join)
			subGroup << word
			@grouped = true
		end
	end
	
	if (false == @grouped)
		groups << [word]
	end
  end

  return groups
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect

# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
