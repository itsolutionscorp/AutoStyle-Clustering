class Prime

  def self.nth(x)
    number = 2
    primes = []

    if x == 0 
      raise ArgumentError
    end
    
    until primes.length == x
      if self.prime?(number) 
        primes << number
      end
      number += 1 
    end

    primes.last
  end

  def self.prime?(number)
    upper_range = number/2
    (2..upper_range).none? { |x| number % x == 0 }
  end
end

p Prime.nth(7)
