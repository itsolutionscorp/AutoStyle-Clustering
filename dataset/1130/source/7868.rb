def combine_anagrams(words)
  
  anagrams = Hash.new
  
  words.each do |str|
     
     sortedString = str.chars.sort.join
     
     if (anagrams[sortedString]== nil)
       anagrams[sortedString] = Array.new
     end
     
     array = anagrams[sortedString]
     array[array.length] = str
     anagrams[sortedString] = array
     array = nil
     
end
 anagrams.values
end