def combine_anagrams(words) 
  anagrams=Hash.new()
  words.each do |word|
    key=word.downcase.split("").sort().join()
    anagram=anagrams[key]
        
    if(anagram==nil) then
      anagrams[key]=Array.new()          
    end
    anagrams[key].push(word)
  end
  
  anagrams_array=Array.new()
  anagrams.keys.each do |key| 
    anagrams_array.push(anagrams[key])
  end
  
  return anagrams_array
end