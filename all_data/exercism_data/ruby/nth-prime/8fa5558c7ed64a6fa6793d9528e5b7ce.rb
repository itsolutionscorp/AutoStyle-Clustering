class Prime
  def self.nth number
    raise ArgumentError if number == 0
    primes = []
    current_number = 1
    while primes.length < number
      if Prime.new.is_prime? current_number 
        primes << current_number
      end
      current_number += 1
    end
    primes.max
  end
  def is_prime? n
    return false if n == 1
    return true if n == 2
    (2..(n-1)).each do |i|
      return false if n % i == 0
    end
    true
  end
end
