def combine_anagrams(words)
  result = []
  words.each do |i|
    x = []
    words.each do |j|
      (x << j) if (i.downcase.chars.sort.join == j.downcase.chars.sort.join)
    end
    (result << x) if (not result.include?(x))
  end
  return result
end

