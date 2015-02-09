def combine_anagrams(words)
  anagrams = []
  words.each do |word|
    sorted_word = word.downcase.chars.sort.join
    toegevoegd = false
    anagrams.map do |anagram_list|
      if (sorted_word == anagram_list[0].downcase.chars.sort.join) then
        (anagram_list << word)
        toegevoegd = true
      end
    end
    (anagrams << [word]) if (toegevoegd == false)
  end
  anagrams
end