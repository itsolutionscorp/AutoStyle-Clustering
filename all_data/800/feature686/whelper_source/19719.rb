def combine_anagrams(words)
  anagrams = []
  sorted_words = []
  words.each do |word|
    match = false
    wsorted = word.downcase.split(//).sort.join
    for index in (0..(sorted_words.length - 1)) do
      if (wsorted == sorted_words[index]) then
        match = true
        (anagrams[index] << word)
        break
      end
    end
    if (not match) then
      (sorted_words << wsorted)
      anagram = []
      (anagram << word)
      anagrams.insert(anagrams.length, anagram)
    end
  end
  anagrams
end

