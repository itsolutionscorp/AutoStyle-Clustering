def combine_anagrams(words)
  anagrams = Array.new
  words.each do |word|
    if(anagrams.length==0) 
      anagrams << Array[word]
    else 
      newanagram = false
      oldanagram = false
      anagrams.each do |anagram|
        if(anagram[0].downcase.chars.sort.join == word.downcase.chars.sort.join)
              anagram << word
           newanagram = false
           oldanagram = true
        else
          newanagram = true
        end
      end

      if(newanagram && !oldanagram)
        anagrams << Array[word]
      end
    end
  end
  
  return anagrams
 
end

