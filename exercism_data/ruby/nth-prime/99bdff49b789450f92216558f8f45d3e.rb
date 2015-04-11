class Prime

  def self.nth(num)
    
    raise ArgumentError if num < 1 
    
    nth = 0
    prime = nil
    tmp_prime = 1
    
    loop do #======================================
      
      tmp_prime += 1
      
      (2..tmp_prime).each do |div_num|
 
        if tmp_prime % div_num == 0
          break if tmp_prime != div_num
          prime = tmp_prime          
          nth += 1          
        end
        
      end
      
      break if nth == num
          
    end #loop end =====================================  
    
    prime
    
  end  
  
end
