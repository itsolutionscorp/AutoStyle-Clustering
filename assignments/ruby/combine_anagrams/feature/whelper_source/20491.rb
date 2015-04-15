def combine_anagrams(words)
  r = Hash.new(0)
  words.each do |s|
    o = s.downcase.chars.sort.join
    r[o] = words.select { |v| (v.downcase.chars.sort.join == o) }
  end
  r.values
end

