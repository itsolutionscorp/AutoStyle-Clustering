def combine_anagrams(words)
  anagram = []
  words.each do |word|
    temp = []
    w = word.downcase.split("").sort
    (temp << word)
    words.each do |i|
      w2 = i.downcase.split("").sort
      (temp << i) if (w == w2) and (word != i)
    end
    (anagram << temp.sort)
  end
  anagram.uniq
end