def combine_anagrams(words)
 k = {}
 words.each do |w| 
q = w.downcase.chars.to_a.sort.to_s
if k[q] == nil then k[q] = [w] else k[q] += [w] end
end
k.values
end

p combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']