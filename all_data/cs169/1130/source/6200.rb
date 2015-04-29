def combine_anagrams(words)
  res = Array.[]()
  words.each do |word|
    sublist = Array.[]()
    words.each do |word2|
      if (word2.downcase.split(//).sort == word.downcase.split(//).sort)
        sublist<<(word2)
      end
    end
    res<<(sublist)
  end
  return res.uniq.sort
end