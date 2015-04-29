def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    sorted = word.chars.sort(&:casecmp).join
    h[sorted] = (h[sorted].to_a + [word])
  end
  a = Array.new
  h.keys.each { |key| a = (a.to_a + [h[key]]) }
  return a
end

