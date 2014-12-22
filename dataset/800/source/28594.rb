def combine_anagrams(words)
  hash = Hash.new([])
  words.each do |w|
    hash[w.downcase.chars.sort.join] += [w]
  end
  return hash.values
end