class PrimeFactors
  attr_accessor :number

  def initialize number
    @number = number
  end

  def self.for number
    results = []
    i = 2
    while number > 1
      while (number % i) == 0
        results << i
        number /= i
      end
      i += 1
    end
    results
  end
end
puts PrimeFactors.for(8)
