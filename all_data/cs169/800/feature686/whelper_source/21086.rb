def combine_anagrams(words)
  group = Hash.new
  group.default = []
  words.each { |m| group[m.to_s.downcase.chars.sort.join] += [m] }
  puts(group.values.inspect)
end

