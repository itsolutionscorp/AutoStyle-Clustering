def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    ang = word.downcase.chars.sort.join
    if (not h.has_key?(ang))
      h[ang] = Array.new
    end
    h[ang].push(word)
  end
  return h.values
end
