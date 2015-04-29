def combine_anagrams(words)
  groups = []
  words.each do |i|
    i_array = []
    words.each { |j| (i_array << j) if (match(i) == match(j)) }
    (groups << i_array)
  end
  groups.map! { |group| group.sort }
  groups.uniq!
  groups
end