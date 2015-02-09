def combine_anagrams(words)
  anagrams = []
  indexes = Hash.new
  fmtd_words = words.collect { |w| w.split("").sort.join("").downcase }
  fmtd_words.each_index do |i|
    word = fmtd_words[i]
    indexes[word] ? ((indexes[word] << i)) : (indexes[word] = [i])
    print(indexes[fmtd_words[i]], fmtd_words[i], i, "\n")
  end
  print(indexes, "\n")
  indexes.each { |_, value| (anagrams << value.collect { |i| words[i] }) }
  return anagrams
end

