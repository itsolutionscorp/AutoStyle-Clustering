def combine_anagrams(words)
  anagrams = []
  word_added = false
  words.each do |word|
    anagrams.each do |anagram|
      if word.downcase.chars.sort.join == anagram[0].downcase.chars.sort.join
        anagram.insert(-1, word)
        word_added = true
      end
    end
      if ! word_added
      anagram = []
      anagram.insert(0, word)
      #puts anagram.to_s
      anagrams.insert(-1, anagram)
    end
    word_added = false
  end
  return anagrams
end