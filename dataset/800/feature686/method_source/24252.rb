def combine_anagrams(words)
  h = Hash.new
  words.each { |w| puts(w) }
  words.each do |w|
    sw = w.downcase.chars.sort.join
    ((h[sw] ||= []) << w)
  end
  return h.values
end