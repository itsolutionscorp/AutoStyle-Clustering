class PrimeFactors
  def self.for(number)
    factors = []
    possible = 2
    until number == 1
      until number % possible != 0
        factors << possible
        number /= possible
      end
      possible += 1
    end
    factors
  end
end
