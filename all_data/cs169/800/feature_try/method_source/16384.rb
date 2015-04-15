def combine_anagrams(words)
  h = Hash.new
  words.each do |w|
    n = norm(w)
    h[n] = [] if (not h.has_key?(n))
    h[n].push(w)
  end
  h.values
end