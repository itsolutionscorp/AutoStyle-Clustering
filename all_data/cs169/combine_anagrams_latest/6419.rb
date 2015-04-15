def combine_anagrams(words)
  g = Hash.new([])
  words.each do |w|
    key = w.downcase.split('').sort.join
    g[key] += [w]
  end
  g.values
end


