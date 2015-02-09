def combine_anagrams(words)
  h = {}
  words.each do |w|
    w_norm = w.downcase.chars.sort.join
    if h[w_norm]
      h[w_norm] = h[w_norm] + [w]
    else
      h[w_norm] = [w]
    end
  end
  h.values
end

