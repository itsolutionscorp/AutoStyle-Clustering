class Prime
  
  def self.nth(n)
    raise ArgumentError if n==0
    primes = []
    num = 2
    while primes.size < n 
      if Prime.is_prime?(num)
        primes << num 
      end
      num+=1
    end
    primes.last
  end
  private 
    def self.is_prime?(num)
      prime = true
      (2...num).each do |div|
        if num % div == 0
          prime = false
          break
        end
      end
      prime
    end
end
