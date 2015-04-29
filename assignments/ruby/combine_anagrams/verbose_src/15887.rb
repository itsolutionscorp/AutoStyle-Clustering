input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

a = ["A","B","c"]

def combine_anagrams(words)
  res = {}
  words.each do |word|
  key=word.split('').sort.join
  res[key] ||= []
  res[key] << word
end
  res.values
end

puts(combine_anagrams(input))
