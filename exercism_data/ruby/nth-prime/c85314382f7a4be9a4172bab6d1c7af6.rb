class Prime
  class << self
    attr_accessor :primes
  end

  @primes = [2,3]

  def self.nth(num)
    num > 0 or raise ArgumentError, "nth input cannot be less than 1"
    
    return @primes[num-1] if num <= @primes.count
      
    i = @primes.last+2
    while @primes.count < num
      @primes << i if i.prime?
      i += 2
    end

    @primes.last
    
  end
end

class Fixnum
  def prime?
    (2..Math.sqrt(self).floor).none? { |divisor| self % divisor == 0 }
  end
end
