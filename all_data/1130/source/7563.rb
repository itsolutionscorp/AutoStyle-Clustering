def combine_anagrams(words)

    if words.kind_of? Array
    word = Array.new
    new_words = words
    
        words.each do |w|
             new_words.each do |nw|
                                            
                       w_arr= w.split(//)
                      
                       nw_arr =nw.split(//)
                       #puts w_arr
                       w_arr =w_arr.sort
                        nw_arr=nw_arr.sort
                         
                                                      
                                 if(w_arr == nw_arr)
                                                                                                          
                                    nw_word = [w , nw]
                                                                          
                                    word << nw_word.sort.uniq
                                    
                                 end                                 
                                
                       
                         end
                                          
                 end
                #word = nw_word   
                
                   word.sort.uniq
                   
                   
                   print word        
                   
    
     
    else 
    puts "This is not an Array"
    
    end

end


