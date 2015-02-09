def combine_anagrams(words)
  aMap = {}
  words.each do |w|
    key = w.downcase.chars.sort
    aMap[key] = (aMap[key] != nil) ? ((aMap[key] + [w])) : ([w])
  end
  aMap.values.to_ary
end

