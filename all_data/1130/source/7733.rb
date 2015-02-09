
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  hash = Hash[]
  res = Array[]
  words.each{
    |word|
    sorted = word.chars.sort { |a, b| a.casecmp(b) } .join.downcase
  print("SORTED: #{sorted}\n")
    if hash.has_key?(sorted)
      hash[sorted].concat([word])
    else
      hash[sorted] = [word]
    end
    print("ADDED: #{hash[sorted]}\n")
  }
  print ("HASH: #{hash}\n")
  hash.each{
    |key, value|
    print ("#{value}\n")
    res.concat([value])
  }
  return res
end

#Example:
# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]

#input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#output = [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

#input = ['A', 'a']
#output = [["A", "a"]]
input = ['HeLLo', 'hello']
output = [["HeLLo", "hello"]]

output_test = combine_anagrams(input)
print("OUT 0: #{output_test.at(0)}\n")
print ("OUT #{output_test}\n")
print ("REF #{output}\n")
if (output == output_test)
  print("OK\n")
else
  print("FAIL\n")
end