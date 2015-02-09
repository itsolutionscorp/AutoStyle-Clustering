def combine_anagrams(words)
  size = 0
  a = Hash.new([])
  words.each do |x|
    r = 0
    y = x.upcase
    y = y.split("")
    y = y.sort
    y = y.join
    a[y] += [x]
  end
  return a.values
end

