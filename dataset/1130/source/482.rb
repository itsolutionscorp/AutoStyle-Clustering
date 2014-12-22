def combine_anagrams(words)
  alpha_sort = words.collect{|word| word.to_s.downcase.chars.sort.join }
  single_words = alpha_sort.uniq

  ret_array = Array.new()
  
  (0..single_words.length-1).each{|s_index|
     temp_arr = Array.new
     (0..alpha_sort.length-1).each{|w_index|
       
       if single_words[s_index].eql?(alpha_sort[w_index])
         temp_arr.insert(temp_arr.length,words.at(w_index))
       end 
       
      }
      ret_array.insert(ret_array.length, temp_arr)
  }
  ret_array
  
end

#print combine_anagrams(['cars', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream'])
