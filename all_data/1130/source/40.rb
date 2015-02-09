def combine_anagrams(words)
  anagrams = []
  words.each do |word|
    group = [word]
    words.each do |other_word|
      if word.downcase.chars.sort == other_word.downcase.chars.sort
        group << other_word
      end
    end
    anagrams << group.uniq.sort
  end
  anagrams.uniq
end