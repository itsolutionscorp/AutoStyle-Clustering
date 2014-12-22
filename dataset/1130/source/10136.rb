def combine_anagrams(words)
h = Hash.new
words.each do |k| 
h[k.downcase.chars.sort.join].nil? ?
h[k.downcase.chars.sort.join] = Array.new.push(k):
h[k.downcase.chars.sort.join].push(k)
end
return h.values
end

p combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','sCar', 'creams', 'scream']
