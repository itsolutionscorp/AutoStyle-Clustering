def combine_anagrams(words)
  anagram_hash = {}
  words.each do |word|
    transformed_word = word.downcase.chars.sort.join
    anagram_hash.has_key?(transformed_word) ? anagram_hash[transformed_word] << word : anagram_hash[transformed_word] = [word]
  end
  anagram_hash.inject([]) {|memo, val| memo << val[1] }
end

anagrams = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts anagrams.inspect


