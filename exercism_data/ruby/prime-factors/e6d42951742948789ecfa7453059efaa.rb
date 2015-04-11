class PrimeFactors
  def self.for(num)
    return [] if num == 1
    factor = (2..num).find { |x| num % x == 0 }
    [factor] + self.for(num / factor)
  end
end
