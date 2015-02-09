def combine_anagrams(words)
  words_cp = Array.new(0)
  words.each { |str| (words_cp << str.downcase.chars.sort.join) }
  words_cp.sort!
  anagrams = Array.new(0)
  while (words_cp[0] != nil) do
    anagram = Array.new(0)
    words.each do |word|
      (anagram << word) if (words_cp[0] == word.downcase.chars.sort.join)
    end
    (anagrams << anagram)
    words_cp.delete(words_cp[0])
  end
  anagrams
end