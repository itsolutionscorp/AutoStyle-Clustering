def combine_anagrams(words)
   
   main_array = Array.new
   
   while words.length!=0 do
     
     word = words.fetch(0);    
      a = Array.new
      a << word       
      startindex =1
   
       while startindex < words.length do
          if( word.downcase.chars.sort.join == words.fetch(startindex).downcase.chars.sort.join )
            a << words.fetch(startindex)                   
          end 
        startindex+=1
       end
      
      main_array << a   
      words = words - a 
    end 
    
   return main_array

end
