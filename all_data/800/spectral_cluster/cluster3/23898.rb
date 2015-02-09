def combine_anagrams(words)

hash = Hash.new
  words.each do |word|
     key = word.downcase.split("").sort.join
     if (hash[key]== nil)
       hash[key] = Array.new
     end
     array = hash[key]
     array.push word
     hash[key] = array
     array = nil
  end
 
  return hash.values

end