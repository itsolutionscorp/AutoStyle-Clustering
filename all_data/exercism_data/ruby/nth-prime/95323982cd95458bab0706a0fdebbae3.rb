class Prime
  
  def self.is_prime?(num)
    (2...num).each do |div|
      return false if num % div == 0
    end
    true
  end
  
  def self.nth(num)
    raise ArgumentError if num == 0

    primes = []
    counter = 2

    while primes.length != num
      primes << counter if is_prime?(counter)
      counter += 1
    end

    primes.last
    
  end

end
