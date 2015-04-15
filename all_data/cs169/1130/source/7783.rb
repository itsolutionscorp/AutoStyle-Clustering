def combine_anagrams(words)
  hash_table = Hash.new(nil)
  words.each do |word|
    signature = word.downcase.chars.sort.join
    puts word + " " + signature
    if(hash_table[signature] == nil)
      hash_table[signature] = [word]
    else
      hash_table[signature] = hash_table[signature].push(word)
    end
  end
  arr = Array.new(0)
  hash_table.each_value do |x|
    arr = arr.push(x)
  end
  arr
end

# input:
# puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
