def combine_anagrams( words )
  agroups = {}
  words.each do	|word| 
	akey = word.downcase.split('').sort
	if agroups.has_key?(akey)
	  agroups[akey] << word
	else
	  agroups[akey] = [word]
	end
  end  
  agroups.map { |p| p[1] }
end
