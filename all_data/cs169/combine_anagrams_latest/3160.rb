def combine_anagrams(words)
  res = []
  words.each do |word|
    r = words.select{|w| w.downcase.split('').sort.join == word.downcase.split('').sort.join}
    res << r
  end
  res.uniq
end
