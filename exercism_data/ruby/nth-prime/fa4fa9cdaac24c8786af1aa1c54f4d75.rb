module Prime

  def self.prime?(p, i)
    p.each {|x| if i % x == 0 then return false end}
    return true
  end

  def self.make_n_primes n
    primes = [2]
    next_to_test = 2
    while primes.length < n
      next_to_test += 1
      if self.prime? primes, next_to_test 
        primes << next_to_test
      end    
    end
    return primes
  end
  
  def self.nth n
    if n < 1 then raise ArgumentError end
    self.make_n_primes(n).last
  end

end
