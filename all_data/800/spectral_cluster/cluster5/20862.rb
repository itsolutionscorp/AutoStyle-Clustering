#anagrams
def combine_anagrams(words)
  words.each {|each|
	for word in words
	  if each.chars.sort.join == word.chars.sort.join
	    return "#{each} is a match"
	  end
	end
  }
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

