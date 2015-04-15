class PrimeFactors
  def self.for(number)
    return [] if number <= 1
    factor = (2..number).find{|n| number % n == 0}
    [factor] + self.for(number / factor)
  end
end
