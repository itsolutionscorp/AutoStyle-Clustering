class Prime
  
  def self.nth n
    raise ArgumentError unless n > 0
    return n + 1 if [1,2].include? n
    
    primes = [2,3]

    3.upto(n) { primes = next_prime_from primes }
    primes.last
  end

  def self.prime? n, primes
    primes.each { |divisor| return false if n % divisor == 0 }
    true
  end

  private 

  def self.next_prime_from primes
    candidate = primes.last + 2
    candidate += 2 until self.prime?(candidate,primes)
    primes << candidate
  end
end
