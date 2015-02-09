def combine_anagrams(words)
  tmp = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    tmp[key] = (tmp[key] or [])
    (tmp[key] << word)
  end
  return tmp.values
end