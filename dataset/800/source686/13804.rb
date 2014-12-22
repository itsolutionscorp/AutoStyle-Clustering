# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  table = Hash.new
  words.each do |word|
    key = word.downcase.chars.to_a.sort
    if table.has_key?(key) then
        table[key] = table[key] + [word]
    else
        table[key] = [word]
    end
  end

  return table.values
end

# input:
#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],)
# ["creams", "scream"]]
