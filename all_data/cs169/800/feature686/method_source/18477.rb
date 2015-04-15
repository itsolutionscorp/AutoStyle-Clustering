def combine_anagrams(words)
  anagrams = []
  words.each do |test_word|
    anagram_group = []
    sorted_test_word = test_word.downcase.chars.sort.join
    words.each do |element|
      sorted_element = element.downcase.chars.sort.join
      if (sorted_test_word == sorted_element) and (not anagrams.flatten.include?(element)) then
        (anagram_group << element)
      end
    end
    (anagrams << anagram_group) unless anagram_group.empty?
  end
  anagrams
end