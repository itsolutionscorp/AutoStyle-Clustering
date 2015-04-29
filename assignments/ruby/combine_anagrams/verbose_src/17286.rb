def combine_anagrams(words)
  @a=Array.new
  @b=Array.new   
    @a = [] + words
    @b = [] + words
  @rtn=[]
@temp=Array.new

  @a.each_with_index do |wor, i|      ## -- @a.each
         @b.each_with_index do |w, l|     
           if wor == w
             @temp << wor
              #@b.delete_at(l)
           end 
         end
       if not @temp.empty?   #  ---1
         @b.each_with_index do |wor1, j|  ### -- @b.each    
          if (wor.downcase).split('').sort.join == (wor1.downcase).split('').sort.join && wor != wor1

              @temp << wor1
           end     
         end    ### -- @b.each
                     
             @rtn << @temp
             @b = @b - @temp
                          
           if i==1 #test
             @test = @b
           end                
           
             @temp=Array.new

              
       end        #  ---1      

   end   ## -- @a.each
      
   return @rtn

  end