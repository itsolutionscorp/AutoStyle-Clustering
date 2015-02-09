# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# # => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
# # HINT: you can quickly tell if two words are anagrams by sorting their
# # letters, keeping in mind that upper vs lowercase doesn't matter
def hashWord(word)
  word = word.downcase
  h = Array.new(26, 0)
  
  word.each_byte do |char|
   h[char.ord - 'a'.ord] += 1
  end
  return h
end

def combine_anagrams(words)
  group = {}
  words.each do |word|
    h = hashWord(word)
    if !group.has_key?(h) then
      group[h] = []
    end
    group[h] = group[h] + [word]
  end
  final = []
  group.each_value do |subset|
    final = final + [subset]
  end
  return final
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
# # => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
