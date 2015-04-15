def combine_anagrams(words)
  hash_anagrams = {}
  words.each{|word| 
			  sorted_word = word.chars.sort(&:casecmp).join
			  if( hash_anagrams.has_key?(sorted_word) == true )
			    hash_anagrams[sorted_word] << word
			  else
			    hash_anagrams[sorted_word] = [word]
			  end
			}
	return hash_anagrams.values
end