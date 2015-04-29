require 'mathn'

class Prime
  
  @@array_of_primes = [2]

  def self.get_array_of_primes
    @@array_of_primes
  end



  def self.nth(inquiry_num)
    if inquiry_num<1
      puts 'ArgumentError: Arugment must be great than 0.'
      raise ArgumentError
    end
    self.generate_array_of_primes_to(inquiry_num) 
    return @@array_of_primes[inquiry_num - 1]     #offset for index 0
  end

  def self.generate_array_of_primes_to(desired_length)
    num = @@array_of_primes.last || 1
    while @@array_of_primes.length < desired_length  
      num +=1
      if num.prime?() then @@array_of_primes.push num end
    end
  end


end
