def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    key = word.downcase.split(//).uniq.sort.join
    h[key] = Array.new if (not h.has_key?(key))
    h[key].push(word)
  end
  return h.values.to_a
end

