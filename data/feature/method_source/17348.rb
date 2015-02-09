def combine_anagrams(words)
  l = []
  words.each do |a|
    (l << words.select do |b|
      (a.downcase.chars.sort.join == b.downcase.chars.sort.join)
    end)
  end
  return l.uniq
end