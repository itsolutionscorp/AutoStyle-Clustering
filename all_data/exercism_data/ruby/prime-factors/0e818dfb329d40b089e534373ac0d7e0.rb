class PrimeFactors
  def self.for(number)
    new(number).factors
  end

  def initialize(number)
    @number = number
  end

  def factors
    factor = 2
    number = @number
    found = []
    while number > 1
      while number % factor == 0
        found << factor
        number /= factor
      end
      factor += 1
    end
    found
  end
end
