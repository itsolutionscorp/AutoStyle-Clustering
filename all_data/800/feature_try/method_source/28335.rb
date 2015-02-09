def combine_anagrams(words)
  anagram_classes = Hash.new
  words.each do |word|
    key = word.downcase.sort
    anagram_classes[key] ||= []
    (anagram_classes[key] << word)
  end
  anagrams = []
  anagram_classes.each_value { |a_class| (anagrams << a_class) }
  anagrams
end