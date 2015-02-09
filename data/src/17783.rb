def combine_anagrams(words)
  hash = words.inject(Hash.new([])) do |h, v|
    h[v.downcase.chars.sort.join] += [v]
    h
  end
  return hash.values
end