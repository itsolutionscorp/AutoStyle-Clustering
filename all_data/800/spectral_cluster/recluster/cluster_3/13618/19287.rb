def combine_anagrams(words) 
  anagramsGroup = []
  words.length.times do |i|
    anagrams = []
    unless words[i].empty?
      anagrams << words[i]
    end
    words.length.times do |j|
	  if (i != j && words[i].downcase != words[j].downcase && words[j] != "" && words[i].chars.sort { |a, b| a.casecmp(b) } .join == words[j].chars.sort { |a, b| a.casecmp(b) } .join)  
            anagrams << words[j]
            words[j] = ""
	  end
    end
     unless anagrams.empty?
      anagramsGroup << anagrams
    end
  end
  
  return(anagramsGroup)
end


p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])