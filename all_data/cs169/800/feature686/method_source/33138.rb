def combine_anagrams(words)
  a = []
  words.each do |i|
    b = []
    words.each do |j|
      (b << j) if (i.downcase.split("").sort == j.downcase.split("").sort)
    end
    (a << b)
  end
  return a.uniq
end