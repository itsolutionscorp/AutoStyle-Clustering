def combine_anagrams(words)
  sorted = Array.new
  words.each do |word|
    #if(sorted[0][0].kind_of?(String))
    match = false
    sorted.each do |w|
      if(sort_compare_words(word,w[0]))
        w[w.count] = word
        match = true
      end
    end
    if(!match)
      sorted[sorted.count] = Array.new
      sorted[sorted.count-1][0] = word
    end
  end
  return sorted
end

def sort_compare_words(word1, word2)
  word1.downcase.chars.sort.join == word2.downcase.chars.sort.join
end
