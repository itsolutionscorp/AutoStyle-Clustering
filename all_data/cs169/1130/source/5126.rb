def combine_anagrams(words)
  anagram = Array.new
  lookup = Hash.new
  
  words.each do |w|
    sorted_word = w.downcase.chars.sort.to_s
    
    if anagram.empty?
      anagram.push([w])
      lookup[sorted_word] = 0
      next
    end
    
    
    if lookup.key? sorted_word
      anagram[lookup[sorted_word]].push(w)
    else
      anagram.push([w])
      lookup[sorted_word] = anagram.index([w])
    end
  end
  return anagram
end

test = ['carS', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 
'scream']

print combine_anagrams(test)
