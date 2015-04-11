class PrimeFactors

  def self.for(n)
    factors = []
    i = 2
    while n > 1
      while n % i == 0
        factors << i
        n = n / i
      end
      i = i + 1
    end
    factors
  end

end
