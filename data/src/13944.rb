def combine_anagrams(words)
  h = {}

  words.each do |word|
    word2 = word.downcase.chars.sort
    if h.has_key?(word2)
      h[word2] = h[word2] + [word]
    else
      h[word2] = [word]
    end
  end
  h.values
end

#print combine_anagrams(['cArs', 'for', 'poTatoes', 'racs', 'four','SCar', 'creams', 'scream'])

# input:
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

