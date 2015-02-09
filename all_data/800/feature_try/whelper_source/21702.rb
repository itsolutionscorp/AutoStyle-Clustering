def combine_anagrams(words)
  grouping = []
  words.each do |x|
    unless grouping.flatten.include?(x) then
      (grouping << words.select do |y|
        (x.downcase.chars.sort.join == y.downcase.chars.sort.join)
      end)
    end
  end
  grouping
end

