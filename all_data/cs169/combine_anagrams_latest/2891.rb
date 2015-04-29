## anagrams, e neiman, 5/29/2012
#array = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#array1 = ['Cars', 'for', 'potatoes', 'racs', 'four','Scar', 'creams', 'scream']
#array2 = ['Cars', 'for', 'potatoes', 'racs', 'four','Scar', 'creams', 'scream', 'cars']
#array3 = ["HeLLo", "hello"]
## => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]

def combine_anagrams(words)
  big_array = Array.new
   hash = Hash.new(0)
   words.each do |w| 
    array = Array.new
    array = w.downcase.split(//)
    if  hash.has_key?(array.sort)  
      hash[array.sort]=hash[array.sort].push(w)
      else
      hash[array.sort]=Array.[](w)
      end
   end
   hash.each { |key,value| big_array.push(value) }
   big_array
end
  
#puts combine_anagrams(array)
#puts "----------------"
#puts combine_anagrams(Array.[])
#puts "----------------"
#puts combine_anagrams(array1)
#puts "----------------"
#puts combine_anagrams(array2)
#puts "----------------"
#puts combine_anagrams(array3)
