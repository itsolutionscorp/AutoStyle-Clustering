class PrimeFactors

  def self.factor number
    (1..number).each {|n| 
      return n + 1 if number % (n + 1) == 0
    } 
  end

  def self.for number
    result = []
    while number > 1
      x = factor(number)
      result << x
      number = number / x
    end
    result
  end

end
