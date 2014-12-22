def combine_anagrams(words)
	out_word_blocks = []
	words.each do |word|
		detected = false;
		out_word_blocks.each do |block|
			if(block[0].downcase.split(%r//).sort.join == 
				word.downcase.split(%r//).sort.join)
				block.push(word)
				detected = true
			end
		end
		unless detected 
			out_word_blocks << [word] #passing as a new block
		end
	end
	
	return out_word_blocks
end

#params = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
#print combine_anagrams(params)