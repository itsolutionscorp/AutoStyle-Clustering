def combine_anagrams(words)
  anagram_list = []
  words.each do |word|
    current_list = []
    words.each do |other_word|
      if word.downcase.split("").sort == other_word.downcase.split("").sort
        current_list << other_word
      end
    end
    anagram_list << current_list.sort
  end
  return anagram_list.uniq
end

