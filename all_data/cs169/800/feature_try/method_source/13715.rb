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