def combine_anagrams(words)
  result = Array.new
  words.each{|word|
    wordArray = Array.new
    wordArray.push(word)
    words.each{|word2|
      if ( (word.chars.sort { |a, b| a.casecmp(b) } .join) == (word2.chars.sort { |a, b| a.casecmp(b) } .join) ) && word != word2 then
        wordArray.push(word2)
        words.delete(word2)
      end
    }
    result.push(wordArray)
  }
  return result
end