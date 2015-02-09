def combine_anagrams(words)
  anagrams = {}
  words.each do |w|
  	s = w.downcase.chars.sort.join
  	if (anagrams[s])
  	  anagrams[s] = (anagrams[s] << w)
  	else
  	  anagrams[s] = [w]
  	end
  end
  anagrams.values
end
