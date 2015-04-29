def combine_anagrams(words)
  hash = Hash.new()
  words.each { |x|  
	  y = hash[x.downcase.chars.sort]
	  if y.nil?
	    hash[x.downcase.chars.sort] = Array.new(1, x)
	  else 
	    hash[x.downcase.chars.sort] = y.push(x)
	  end
  }
  return hash.values
end
  
 #combine_anagrams (['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])