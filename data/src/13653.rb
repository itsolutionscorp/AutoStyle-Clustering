def combine_anagrams(words)
  a = words.map { |i| i.downcase.chars.sort.join }
  b = a.uniq
  htable = Hash.new
  b.each { |w| htable[w] = [] }
  b.each { |w| a.each_with_index { |x, y| (htable[w] << y) if (x == w) } }
  result = []
  t = []
  htable.each do |x, y|
    y.each { |i| (t << words[i]) }
    (result << t)
    t = []
  end
  return result
end