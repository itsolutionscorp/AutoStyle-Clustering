def combine_anagrams(words)
  
  anagram = Array.[]
  
  h = Hash.new(0)
  words.each do |w|
    hash = w.downcase.chars.sort.join 

    unless h.has_key?(hash) 
      h[hash] = Array.[]
    end

    h[hash].push(w)
  end

  h.each_value { |value| anagram.push(value) }
  
  anagram
  
end

# input:
#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind 