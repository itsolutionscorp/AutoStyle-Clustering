def combine_anagrams(words)
  groups = []
  words.each do |x|
    inc_bool = false
    groups.each { |y| inc_bool = true if y.include?(x) }
    if (inc_bool == false) then
      group = []
      (groups << group)
      words.each do |y|
        if (x.upcase.chars.sort.join == y.upcase.chars.sort.join) then
          (groups.last << y)
        end
      end
    end
  end
  return groups
end