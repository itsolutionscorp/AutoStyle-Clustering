#anagrams
# input:['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  output = Array.new
  words.each { |word|
     if output.length.eql?0 then output.push [word];
     else
       found = false
       output.each { |anagram|
         if(anagram[0].downcase.chars.sort.join.eql? word.downcase.chars.sort.join)  
           anagram.push word
           found = true
         end  
       }
       if found.eql?false then output.push [word]; end
     end
  }
  output
end

input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
output = combine_anagrams input
output.each { |anagram|
  print "\tANAGRAM #{anagram}\n"
}
