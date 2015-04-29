def combine_anagrams(words)
	if words.empty? then return [];end;
	anagrams=[]
	while !words.empty?
		word,*words = words
		word_anags=[word]
		word_anags += words.select{|p_anag|
			word.downcase.chars.sort.join.casecmp(p_anag.downcase.chars.sort.join)==0
		}
		
		word_anags.delete_if{|item| item.empty?}
		words-=word_anags
		anagrams << word_anags
	end
	anagrams
end

#print combine_anagrams ['cars', 'for', 'potatoes', 'rAcs', 'four','scar', 'creams', 'Scream']
#print combine_anagrams []
