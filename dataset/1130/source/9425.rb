def combine_anagrams(words)
result= Hash.new(Array.new())
words.each {|word| 
result[word.downcase.split('').sort.join] += [word]
 }
p result.values
return result.values
end