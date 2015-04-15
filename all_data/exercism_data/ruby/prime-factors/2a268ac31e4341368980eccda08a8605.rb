module PrimeFactors
  def self.for(n, div = 2)
    factors = []
    until n == 1
      if n % div == 0
        factors << div
        n /= div
      else
        div += 1
      end
    end
    factors
  end
end
