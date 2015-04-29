# h1p3.rb




def combine_anagrams(words)

  if words.empty?
    return words
  end  
  
  words_copy = words.dup  # makes an independent copy
  
  a=Array.new(0)
  a[0]=words.shift(1)
  
  an_group=""
  got_one=false 
   
   
  words_copy.each do 
    
    
    #puts a[0].to_s.chars.sort.join
    zcomp = a[0].to_s.gsub /[\[\]"]/,""
    
    
    if an_group.downcase.include? zcomp.downcase
      a[0]=words.shift(1)
    else  
    
        an_group=an_group+'["'+zcomp+'"'
        
        zcomp = zcomp.downcase.chars.sort.join  # eg cars -> acrs
        #puts "comparing "+a[0].to_s   
        words.each do |entry|
          #puts entry
          if entry.downcase.chars.sort.join == zcomp
            #puts "anigram found: "+entry + " : "+a[0].to_s
            got_one=true
            an_group=an_group+', "'+entry+'"'
          end 
          
        end 
        an_group=an_group+"], "
                                 
        a[0]=words.shift(1)

        
     end     # if an_group.include 
        
  end   # next words_copy
  
  an_group="["+an_group.chomp!(", ")+"]"
  
  
  
  retval = eval(an_group)
  
   return retval
  
end  