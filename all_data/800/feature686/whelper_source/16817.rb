def combine_anagrams(words)
  h = {}
  words.each do |word|
    idx = word.downcase.chars.sort.join
    h[idx] ||= []
    (h[idx] << word)
  end
  h.values
end

