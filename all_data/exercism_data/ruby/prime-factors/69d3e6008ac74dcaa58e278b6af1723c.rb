class PrimeFactors

  def self.for(n)
    @primes = []
    is_prime(n) unless n == 1
    return @primes
  end  

  private

  def self.is_prime(n)
    (2..n).each do |i|
      if n%i == 0
        @primes << i
        is_prime(n/i)
        break
      end
    end
  end

end
