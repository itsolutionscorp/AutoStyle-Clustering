def combine_anagrams(words)

  result = []

  words.each do |word|

    is_anagram = false

    result.each do |anagram|
      if is_anagram(word,anagram[0])
        anagram << word
        is_anagram = true
      end
    end

    if is_anagram == false
      result << [word]
    end

  end
  
  return result

end

def is_anagram(word_a,word_b)

  word_a = word_a.downcase.chars.sort().join
  word_b = word_b.downcase.chars.sort().join

  return word_a == word_b
    
end
