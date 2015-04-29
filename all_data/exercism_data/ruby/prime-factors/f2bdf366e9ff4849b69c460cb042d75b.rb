module PrimeFactors
  extend self

  def for(number)
    @number = number
    [*factors_for(2), *odd_factors]
  end

  private

  def odd_factors
    factors = []
    candidate = 3
    until @number == 1
      factors.push( *factors_for(candidate) )
      candidate += 2
    end
    factors
  end

  def factors_for(candidate)
    factors = []
    while @number % candidate == 0
      factors << candidate
      @number /= candidate
    end
    factors
  end
end
