def combine_anagrams(words)
  h = {}
  words.each do |word|
    group = word.downcase.chars.sort.join
    (not h.has_key?(group)) ? (h[group] = [word]) : (h[group].push(word))
  end
  h.values
end

