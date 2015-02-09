def combine_anagrams(words)
  h_words = Hash.new { |h, k| h[k] = [] }
  words.each { |word| (h_words[word.downcase.chars.sort.join] << word) }
  a_lists = Array.new(0)
  h_words.each_pair { |k, v| (a_lists << v) }
  a_lists
end

