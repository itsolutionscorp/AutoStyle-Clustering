def combine_anagrams(words)
   array = [];
   result = [];
   
   for x in words
      array = array.unshift(x.downcase.split('').sort.join.gsub(/(.)\1{2,}/) { |x| x.length.to_s + x[0,1] } );
   end
  
   array.reverse!
   tmp = []
   indice = 0
   
   for element in array
      
      indice = array.find_index(element.to_s);
      
      while( indice != nil) 
          tmp.unshift(words[indice]);
          array[indice] = 0;
          indice = array.find_index(element.to_s) ;
         
      end 
   
      if tmp != []
         result.unshift(tmp.reverse)
         tmp = []; 
      end   
      
   end
   result.reverse!

end
