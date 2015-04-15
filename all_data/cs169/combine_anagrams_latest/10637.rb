def combine_anagrams(words) 
  results = Hash.new
  results_array = Array.new
  
  words.each { |word|
      #puts word
      
      processed_word = word.upcase.chars.sort.join
      
      #puts processed_word

      if !results.has_key? processed_word
        #puts "1"
        results[processed_word] = Array.new
        results[processed_word].insert(0, word)
      else 
        #puts "2"
        results[processed_word] = results[processed_word].insert(0, word)
      end
  }
  
  #puts results.length
  #puts results
  
  if results.length > 0
    #puts "IN"
    
    results.each { |key, value|
      #puts key
      #puts value
      
      results_array << value
    }
  end  
  
  #puts results_array.length
  
  return results_array
end

#input = []
#puts combine_anagrams(input) 
#input = ['A']
#puts combine_anagrams(input)
#input = ["ab", "ba", "AB", "b", "c", "C"]
#puts combine_anagrams(input)
#input = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'] 
#puts combine_anagrams(input)# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]