def combine_anagrams(words)
  h = Hash.new
  a = Array.new
  words.each do |wo|
    w = wo.downcase.chars.sort.join
    (not h.member?(w)) ? (h[w] = [wo]) : (h[w] += [wo])
  end
  h.each { |key, value| a.push(value) }
  return a
end