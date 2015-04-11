class Raindrops

  def convert(number)
    result = PrimeNumber.for(number)
    string = ''

    if result.include?(3)
      string << "Pling"
    end

    if result.include?(5)
      string << "Plang"
    end

    if result.include?(7)
      string << "Plong"
    end

    if !result.include?(3) && !result.include?(5) && !result.include?(7)
      string = number.to_s
    end

    string
  end
end

class PrimeNumber
  
  def self.for(num)
    prime_factors = []
    divisor = 2

    while num > 1
      while (num % divisor) == 0
        prime_factors << divisor
        num /= divisor
      end
      divisor += 1
    end
    prime_factors
  end
end
