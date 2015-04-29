def combine_anagrams(words)
  res = {}
  words.each do |w|
    w_hash = w.downcase.split(//).sort.join.hash
    if (res.has_key?(w_hash))
      res[w_hash] << w
    else
      res[w_hash] = [w]
    end
  end
  res.values
end
