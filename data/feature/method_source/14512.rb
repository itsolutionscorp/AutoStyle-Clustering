def combine_anagrams(words)
  res = {}
  words.each do |w|
    w_sorted = w.downcase.chars.sort.join
    res[w_sorted].nil? ? (res[w_sorted] = [w]) : (next)
    ((words.index(w) + 1)..(words.count - 1)).each do |i|
      w2 = words[i]
      w2_sorted = w2.downcase.chars.sort.join
      (res[w_sorted] << w2) if (w_sorted == w2_sorted)
    end
  end
  res.values
end