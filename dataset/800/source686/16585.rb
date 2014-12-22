def combine_anagrams(words)
  occurance = Hash.new

  words.each  { |word|
    normalWord = word.downcase.chars.sort.join
    if occurance[normalWord].nil? then
      occurance[normalWord] = [word]
    else
      occurance[normalWord].push(word)
    end             
  }
    
  retVal = []
  occurance.each { |key, value|  
    retVal.push(value) 
  } 
  return retVal

end

