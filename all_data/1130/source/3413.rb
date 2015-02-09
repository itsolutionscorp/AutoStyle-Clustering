def combine_anagrams(words)
  resultHash = Hash.new(0)
  
  words.each { |word|
    aWord = word.downcase.chars.sort.join 
    result = resultHash[aWord]
    if result == 0
      resultHash[aWord] = Array.new << word
    else
      resultHash[aWord] = result << word
    end
  }
    return resultHash.values
end