def combine_anagrams(words)
  map = {}
  words.each{|x| map[x.downcase.chars.sort.join] = map[x.downcase.chars.sort.join].nil? ? [x] : map[x.downcase.chars.sort.join]<<x}
  map.values
end
