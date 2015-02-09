def combine_anagrams(words)
  h = Hash.new { [] }
  words.each do |word|
    key = word.downcase.chars.sort.join
    h[key] = (h[key] << word)
  end
  return h.values
end