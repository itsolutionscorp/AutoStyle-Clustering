def combine_anagrams(words)
  h = Hash.new()
  words.uniq.map {|word| h[word] = word.downcase.split(//).sort.join}
  res = Hash.new()
  h.each do |key,val|
    res[val] = h.select {|k,v| v==val }.keys
  end
  res.values
end