class Prime
  def nth(n)
    raise ArgumentError if n < 1
     
    @primes = Array.new
    @x = 2
    
    until @primes.size == n do
      @primes << @x if @x.is_prime?
      @x += 1
    end
    @primes.last
  end
  
end

class Fixnum
  def is_prime?
    return false if self <= 1
    2.upto(Math.sqrt(self).to_i) do |x|
      return false if self % x == 0
    end
    true
  end
end
