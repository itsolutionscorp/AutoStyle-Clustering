def to_normal(string)
  return string.downcase.chars.sort.join
end

def combine_anagrams(words)
result = Array.new
words.each { |word|
flag = false;
unless result.empty? 
  result.each { |array|
     if to_normal(word) == to_normal(array[0])
        flag = true;
        array<<word
     end
   }
end
unless flag
new = Array[word]
result<<new
end
}
return result
end

#test = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#p combine_anagrams(test)