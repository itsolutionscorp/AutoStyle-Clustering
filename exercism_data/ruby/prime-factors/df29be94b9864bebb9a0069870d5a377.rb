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
      if number % factor == 0
        found << factor
        number /= factor
      else
        factor += 1
      end
    end
    found
  end

  # I need tail recursion :)
  #def find_factors(factor, found, number)
  #  return found if number == 1

  #  if number % factor == 0
  #    find_factors(factor, found + [ factor ], number / factor)
  #  else
  #    find_factors(factor + 1, found, number)
  #  end
  #end
end
