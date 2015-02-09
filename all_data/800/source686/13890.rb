def combine_anagrams(words)
  array1 = Array.new(0)
  
  words.each do |word1|
    array2 = Array.new(0)
    words.each do |word2|
      if (word1.downcase.split(//).sort == word2.downcase.split(//).sort)
        array2.push(word2)
      end
    end
    array1.push(array2)
  end
  
  return array1.uniq
end