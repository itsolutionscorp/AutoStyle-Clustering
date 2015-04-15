def combine_anagrams(words)
  h = Hash[]
  words.each { |i| if h[i.downcase.chars.sort.join].nil? then h[i.downcase.chars.sort.join] = [i] else h[i.downcase.chars.sort.join].push(i) end } #.group_by { |i| i }.map { |i| i[1].to_a }
  h.to_a.map{|i| i[1]}
end

#input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#output = combine_anagrams input
#p output

# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter