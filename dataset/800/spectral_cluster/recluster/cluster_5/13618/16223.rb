def combine_anagrams(words)
ahash = Hash.new
words.each do |w|
  sw = w.split(%r{\s*}).sort.to_s
  wg = ahash.fetch(sw, nil)
  if (wg == nil)
    wg = Array.new
    wg << w
    ahash.store(sw, wg)
  else
    wg << w
  end
end

anagrams = Array.new
ahash.each_value {|wg| anagrams << wg}
anagrams
end
# input:
# combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# # => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
# # HINT: you can quickly tell if two words are anagrams by sorting their
# # letters, keeping in mind that upper vs lowercase doesn't matter
