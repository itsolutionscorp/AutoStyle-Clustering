def is_anagram(word1, word2)
  str1 = word1.downcase.chars.sort.join("")
  str2 = word2.downcase.chars.sort.join("")
  return (str1 == str2)
end

def combine_anagrams(words)
  anagrams = []
  words.each do |word|
    existed = false
    anagrams.each do |anagram|
      if is_anagram(anagram[0], word) then
        anagram.push(word)
        existed = true
        break
      end
    end
    anagrams.push([word]) if (not existed)
  end
  return anagrams
end

