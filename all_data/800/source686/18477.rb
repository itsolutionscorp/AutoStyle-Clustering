def combine_anagrams(words)
  anagrams = []
  words.each do |test_word|
     anagram_group = [] 
     sorted_test_word = test_word.downcase.chars.sort.join
     words.each do |element|
        sorted_element = element.downcase.chars.sort.join
        if sorted_test_word == sorted_element && !anagrams.flatten.include?(element)
            anagram_group << element
        end
     end
     unless anagram_group.empty? 
         anagrams << anagram_group
     end
  end
  anagrams
end

# result = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream','dog','God'])
# puts "result is #{result}"