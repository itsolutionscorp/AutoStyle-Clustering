def combine_anagrams(words)
  
  sortedWords = Hash.new([])
  
  words.each do |word|

     if sortedWords.key?([(word.downcase.chars.sort_by(&:downcase).join)])
        wordArray = sortedWords.fetch([(word.downcase.chars.sort_by(&:downcase).join)])
        wordArray << [word]
        else
        wordArray = [word]
   end
    
 
   sortedWords.store([(word.downcase.chars.sort_by(&:downcase).join)], wordArray)

  end
 return sortedWords.values.to_a
  
end
