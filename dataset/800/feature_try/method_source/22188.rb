def combine_anagrams(words)
  h = Hash.new
  words.each do |w|
    sort = w.downcase.chars.sort { |a, b| a.casecmp(b) }.join
    (h[sort] == nil) ? (h[sort] = [w]) : (h[sort][h[sort].length] = w)
  end
  return h.values
end