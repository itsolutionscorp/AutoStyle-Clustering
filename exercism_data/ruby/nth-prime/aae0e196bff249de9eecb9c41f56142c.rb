class Prime
  
  def self.nth(n) 
    raise ArgumentError if n == 0 
    primes = [2,3]
    if n > 2
      i=primes.last
      loop do
        i+=1
        primes << i if prime?(i, primes)
        break if primes.length==n
      end
    end 
    primes[n-1]
  end  

  def self.prime?(n, primes)
    primes.each do |p|
      return false if n % p == 0
    end  
    true
  end  
  
end  
