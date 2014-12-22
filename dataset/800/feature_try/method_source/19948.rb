def combine_anagrams(words)
  res = []
  words.each do |word|
    found, idx = findAnagram(res, word)
    (found == false) ? ((res << [word])) : ((res[idx] << word))
  end
  res
end