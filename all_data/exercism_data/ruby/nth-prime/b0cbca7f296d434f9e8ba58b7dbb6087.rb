class Prime
 
  def self.nth position
    
    primes_count = 1
    number = 1  
    array_of_primes = Array.new

    raise ArgumentError if position == 0
    
    while primes_count <= position
       number += 1
      if self.is_prime? number
        primes_count += 1  
      end
    end 
    
    number
  
  end

  
    def self.is_prime? number

    (2...number).none? do |dividend| 
      number % dividend == 0
    end
  
  end

end
