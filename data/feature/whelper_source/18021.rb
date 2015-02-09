def combine_anagrams(words)
  h = Hash[]
  words.each do |w|
    h[w.downcase.chars.sort.join] = (h[w.downcase.chars.sort.join].to_a << w)
  end
  return h.to_a.collect { |kv| kv[1] }
end

