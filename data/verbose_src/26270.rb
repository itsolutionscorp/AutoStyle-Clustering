def combine_anagrams(words)
		
	all_groups = []
	if words.length == 0
		return all_groups
	end
	
	begin
		ana_group = []
		ana_group << words[0]
		words.delete_at(0)
		if words.length > 0
			words.reverse.each {
				|wrd| 
				if wrd.downcase.split(//).sort.join == ana_group[0].downcase.split(//).sort.join
					ana_group << wrd
					words.delete_at(words.rindex(wrd))
				end
			}
		end
		all_groups << ana_group
	end while words.length > 0
	
	return all_groups
end