#!/usr/bin/ruby

def combine_anagrams(words)
  result = []
  while words.length > 0 do
    w = words.first
    temp = []
    temp.push(w)
#    words.each { |w2|
     1.upto(words.length) { |i|
      w2 = words[i]
#      if w != w2 && w.downcase.chars.sort.join == w2.downcase.chars.sort.join
      if w2 != nil && w.downcase.chars.sort.join == w2.downcase.chars.sort.join
        temp.push(w2)
      end
    }
    result.push(temp)
    temp.each { |elem|
      words.delete(elem)
    }
    words.delete(temp)
  end
  
  print result
  return result
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])