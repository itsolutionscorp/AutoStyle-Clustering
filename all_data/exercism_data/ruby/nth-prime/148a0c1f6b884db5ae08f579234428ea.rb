class Prime
  def self.nth(n)
    raise ArgumentError.new "n must be positive" unless n >= 1
    
    primes = []
    curr = 2
    while primes.length < n do
      primes << curr if prime?(curr, primes)
      curr += 1
    end
    primes.last
  end
  
  def self.prime?(number, lower_primes)
    lower_primes.each do |i|
      return false if number % i == 0
    end
    return true
  end
end

puts Prime.nth(1)
