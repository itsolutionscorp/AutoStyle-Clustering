require 'prime'

class Prime  
  def nth(num)
    
    # Googled how to raise an error to pass the last test and this seems to work!
    if num == 0
      raise ArgumentError
    end
    
    i = 0
    prime_found = false
    prime_count = 0
    
    while !prime_found do
       i +=1
       if i.prime?
         prime_count += 1
         if prime_count == num 
           prime_found = true
         end
       end       
    end    
    i
  end  
end
