def combine_anagrams(words)
  groups = Hash.new([])
  words.each do |w|
    ww = w.downcase.chars.to_a.sort.to_s
    groups[ww] += [w]
  end
  groups.map { |k, v| v }
end