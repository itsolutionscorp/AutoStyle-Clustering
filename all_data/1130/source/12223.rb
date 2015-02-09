input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# output:["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)  
  anagrams = words.inject(Hash.new()) do |r, word|
    key = word.downcase.chars.sort.join
        r[key] ||=[]
        r[key] << word
        r
  end
    anagrams.values
end

puts(combine_anagrams(input))