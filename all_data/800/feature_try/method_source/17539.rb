def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    letters = word.downcase.each_char.sort
    h[letters] ? (h[letters].push(word)) : (h[letters] = [word])
  end
  return h.values
end