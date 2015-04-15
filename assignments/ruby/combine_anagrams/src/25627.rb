def combine_anagrams(words)
  res = Hash.new([])
  words.each do |word|
    w = word.downcase.chars.sort.join
    res[w] = (res[w] + [word])
  end
  res.values
end