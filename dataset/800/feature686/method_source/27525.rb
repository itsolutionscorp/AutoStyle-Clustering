def combine_anagrams(words)
  ana = Hash.new([])
  words.each do |w|
    tempKey = w.downcase.chars.sort.join
    ana.has_key?(tempKey) ? ((ana[tempKey] << w)) : (ana[tempKey] = [w])
  end
  return ana.values
end