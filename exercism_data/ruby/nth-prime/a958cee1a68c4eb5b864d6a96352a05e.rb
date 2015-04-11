class Prime
  
  def self.nth(given_prime_to_check)
    prime_counter, result = 0, 2
    
    until prime_counter == given_prime_to_check
      (prime_counter += 1) if !(2...result).to_a.map{|j| result % j}.include?(0)
       result += 1
    end
    result-1
  end
  
end
