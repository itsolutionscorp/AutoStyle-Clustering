def combine_anagrams(words)

  anagrams = {}
  words.each do |word|
    if anagrams.include?(word.downcase.chars.sort.join)
      anagrams[word.downcase.chars.sort.join] = anagrams[word.downcase.chars.sort.join] << word
     else
      anagrams[word.downcase.chars.sort.join] = []<<word
    end
  end
  
  #retVal = []
  
  #anagrams.each do |letters, ocurencies|
 #   retVal << ocurencies
 # end

  #retVal
  anagrams.values

end