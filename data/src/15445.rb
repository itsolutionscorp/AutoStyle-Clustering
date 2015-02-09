def combine_anagrams(words)
  result = []
  processed = []
  words.each do |word1|
    unless processed.include?(word1) then
      innerRes = [word1]
      words.each do |word2|
        unless (word1 == word2) then
          if (word1.downcase.chars.sort.join == word2.downcase.chars.sort.join) then
            innerRes = (innerRes + [word2])
            processed = (processed + [word2])
          end
        end
      end
      result = (result + [innerRes])
    end
  end
  return result
end