def combine_anagrams(words)
  h = {}
  words.each do |word|
    group = word.downcase.chars.sort.join
    if not h.has_key? group then h[group] = [word] else h[group].push word end
  end
  h.values
end
