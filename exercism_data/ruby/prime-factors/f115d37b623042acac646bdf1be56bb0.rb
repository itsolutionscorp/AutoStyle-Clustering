class PrimeFactors
  def self.for n, factors=[]
    return factors if n < 2
    factor  = (2..n).detect { |el| n % el == 0 }
    PrimeFactors.for n/factor, factors+[factor]
  end
end
