def combine_anagrams(words)
  
  result = Array.new
  
  words.each do |word|
#    puts "Starting sequence for #{word}"
    if result.length == 0
#      puts "Adding first word to array: #{word}"
      temp_arr = Array.new
      temp_arr += [word]
      result[0] = temp_arr
 #     puts "result array #{result}"
    else
      i = 0
      current_length = result.length
      added = false
      while i < current_length and not added do
        result_word = result[i][0]
 #       puts "result word #{result_word} at position #{i}"
        
        if result_word.downcase.chars.sort.join == word.downcase.chars.sort.join
          result[i] += [word]
 #         puts "#{result_word} is an anagram of #{word}" 
          added = true   
        end 
        
        i = i+1     
      end
      
      if added == false
#          puts "Adding #{word} to result array"
          temp_arr = Array.new
          temp_arr += [word]
          result[result.length]= temp_arr
       end 
    end 
    
    
  end
  return result
  
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

