def combine_anagrams(words)
  combined_anagrams = []
  words.each do |first_word|
    next if (first_word == nil)
    anagram = [first_word]
    words.after(first_word).each do |next_word|
      next if (next_word == nil)
      if (first_word.sort_letters == next_word.sort_letters) then
        (anagram << next_word)
        words[words.index(next_word)] = nil
      end
    end
    words[words.index(first_word)] = nil
    (combined_anagrams << anagram)
  end
  combined_anagrams
end