# input:
inp = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  a_hash = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    a_hash[key] = a_hash.fetch(key, []) << word
  end
  return a_hash.values
end

if __FILE__ == $0
  puts "Result: #{combine_anagrams(inp)}"
end

