def combine_anagrams(words)
  anagrams = Hash.new()
  words.each do |word|
    ordered = word.downcase.chars.sort.join
    anagrams[ordered] ? anagrams[ordered] << word : anagrams[ordered] = [word]
  end
  return anagrams.values
end
#
# # input:
# puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
# #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# puts combine_anagrams(["creams", "scream"]).to_s
# # HINT: you can quickly tell if two words are anagrams by sorting their
# #  letters, keeping in mind that upper vs lowercase doesn't matter
