def combine_anagrams(words)
  h = Hash.new([])
  words.each { |w| h[w.downcase.chars.sort.join] += [w]}
  res = []
  h.each_key {|key| res << h[key] }
  res
end