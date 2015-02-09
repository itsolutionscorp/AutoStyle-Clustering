def combine_anagrams(words)  
  result = {}
  words.each do |word1| 	
	words.each do |word2|		
		soretdWord = word2.chars.sort.join					
		if (result.has_key? soretdWord)
			puts "tiene la clave " + soretdWord
			if (!result[soretdWord].include?(word2))
				puts "no esta repetido agrego el elemento " + word2
				result[soretdWord]  << word2
			else
				puts "pero " + word2 + " ya esta"
			end					
		else
			result.store(soretdWord,[word2])	
			puts "agrego " + word2 + " por primera vez"
		end		
	  end
	end
	puts
  return result.values
end

