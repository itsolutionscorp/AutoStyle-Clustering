
def combine_anagrams(words)
  h = {}
  words.each do |w|
    letters = w.downcase.chars.to_a.sort
    h[letters] ||= []
    h[letters] << w
  end
  h.values
end
