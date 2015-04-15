def combine_anagrams(words)
  h = {}
  words.each do |w|
    key = w.downcase.each_char.sort.join
    h[key] ? h[key] << w : h[key] = [w]
  end
  h.values
end
