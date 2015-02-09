def combine_anagrams(words)
  dict = {}
  words.each do |w|
    x = w.chars.sort.join.downcase
    dict.has_key?(x) ? (dict[x] = (dict[x] << w)) : (dict[x] = [w])
  end
  return dict.values
end