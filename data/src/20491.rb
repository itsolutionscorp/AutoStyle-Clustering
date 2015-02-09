def combine_anagrams(words)
  r = Hash.new(0)
  words.each { |s| o=s.downcase.chars.sort.join; r[o] = words.select { |v| v.downcase.chars.sort.join == o } }
  r.values
end
