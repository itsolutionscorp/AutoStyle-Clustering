def combine_anagrams(words)
  word_hash = {}
  words.each { |word|
    puts word
    tword = word.downcase.chars.sort.join
    puts tword
    if word_hash[tword] == nil
      word_hash[tword] = [word]
    else
      word_hash[tword] << word
    end  
  }
  outArray = []
  word_hash.each { |k,v|
    puts k
    tArray = []
    v.each { |vv|
      tArray << vv  
    }
    outArray << tArray
  }
  return outArray
end