class Prime
  PRIMES = [2]
  def self.nth(num)
    raise ArumentError if num < 1

    self.fill_prime_array_upto(num)

    PRIMES[num-1]
  end 

  def self.fill_prime_array_upto(num)
    return if num <= PRIMES.length 
 
    (num - PRIMES.length).times do
      next_possible_num = (PRIMES.last + 1) 
      while not_prime(next_possible_num)
        next_possible_num += 1  
      end 
      
      PRIMES.push(next_possible_num)
    end
  end 

  def self.not_prime(next_possible_num)
    PRIMES.any? {|p| next_possible_num % p == 0}
  end 

end
