class PrimeFactors
  def self.for(n)
    factors = []
    until n == 1
      factor = (2..n ** 0.5).find {|f| (n % f).zero?} || n
      n /= factor
      factors << factor
    end
    factors
  end

  def self.for_tco(n, factors = [])
    return factors if n == 1
    factor = (2..n ** 0.5).find {|f| (n % f).zero?} || n
    self.for(n / factor, factors << factor)
  end
end
