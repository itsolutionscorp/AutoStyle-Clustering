def combine_anagrams(words)
	words_dc_sorted=Array.new
	words.each do |word|;	words_dc_sorted.push(word.downcase.chars.sort.join);	end
	
	ana_hash=Hash.new
	0.upto(words.length-1) do |i|
		word_sorted=words_dc_sorted[i];		word=words[i]
		if ana_hash[word_sorted]==nil; 	ana_hash[word_sorted]=Array.new; 	end
		ana_hash[word_sorted].push(word)
	end
	
	ana_list=Array.new
	ana_hash.each_value {|group| ana_list.push(group)}
	return ana_list
end


# input:
#words=['caRs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#print combine_anagrams(words)
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
