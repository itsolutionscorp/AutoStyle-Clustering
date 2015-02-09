def combine_anagrams(words)
  a = Array.new
  b = Array.new
  words.each do |x|
    a = words.select do |y|
      (y.downcase.chars.sort.join == x.downcase.chars.sort.join)
    end
    b.push(a) unless (a == [])
    words = (words - a)
  end
  return b
end

