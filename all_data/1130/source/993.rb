def combine_anagrams (words)
  anagrams = Array.new
  words.each do |word|
    foundAnagram = false
    anagrams.each do |anagram|
      if anagram?(anagram[0], word)
        anagram.push(word)
        foundAnagram = true
        break
      end
    end
    if !foundAnagram
      anagrams.push(Array.new.push(word))
    end
  end
  return anagrams
end

def anagram?(word1, word2)
  return word1.downcase.chars.sort.join == word2.downcase.chars.sort.join
end
