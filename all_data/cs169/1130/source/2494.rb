def compare(word1, word2)
	word1 = word1.downcase.split(//).sort.join
	word2 = word2.downcase.split(//).sort.join
	return word1 == word2
end

def do_match(word, words)	
	result = [word]
	new_words = Array.new(words)	
	indices = []
	
	words.each_with_index do |t,index|			
		if compare(word, t)			
			result << t		
			indices << index
		end
	end
		
	indices.sort!.reverse!	
	indices.each{ |i| words.delete_at(i) }	
			
	return [result.sort, words]	
end



def combine_anagrams(words)
	
	result = Array.new
	
	while (words.length>0)
		word = words[0]
		words.delete_at(0)
		output = do_match(word, words)		
		result << output[0]				
		words = output[1]
	end
			
    result
end

  puts combine_anagrams(["a", "b", "A", "B", "B", "A"]).inspect