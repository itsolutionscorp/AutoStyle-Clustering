def combine_anagrams(words)
  anagram_groups = Hash.new
  
  words.each do |word|
    sorted_word = word.downcase.each_char.sort.join
    if anagram_groups.has_key?(sorted_word)
      anagram_groups[sorted_word] << word
    else
      anagram_groups[sorted_word] = [word]
    end
  end
  
  anagram_groups.values
end
