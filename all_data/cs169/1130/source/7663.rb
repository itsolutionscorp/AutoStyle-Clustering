def combine_anagrams(words)
	resultado = Array.new
	anagramas = Array.new
	words.each { |word|
		if resultado.length == 0 
			resultado << word
			anagramas << word
		else
			encontrado = false
			i=0
			anagramas.each { |anagram|
				printf("%d %s %s\n", i,word,anagram) 		
				if word.downcase.chars.sort.join == anagram.downcase.chars.sort.join
					encontrado = true
					resultado[i] = [resultado[i].flatten, word]
					#break
				end
				i = i + 1
			}
		end
		if encontrado == false 
			resultado << word
			anagramas << word
		end
		puts 
	}
	puts	
	resultado.each{|r| puts r}
	return resultado
end