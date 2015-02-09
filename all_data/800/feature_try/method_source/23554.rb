def combine_anagrams(words)
  sorted = Hash.new
  k = 0
  words.each do |c|
    sorted[k] = c.downcase.chars.sort.join
    k = (k + 1)
  end
  anagram = Array.new
  a = Array.new
  b = Array.new
  i = 0
  sorted.invert.each do |w, y|
    a[i] = sorted.select { |k, v| (v == w) }.keys
    a[i].each { |x| (b << words[x]) }
    (anagram << (anagram[i].to_a + b.to_a))
    b.clear
    i = (i + 1)
  end
  return anagram
end