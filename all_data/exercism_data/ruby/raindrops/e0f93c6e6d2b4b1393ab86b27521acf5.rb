class Raindrops

  def convert(number)
    prime_factors = PrimeFactors.for(number)
    plingplangplong = inspect_factors(prime_factors)
    plingplangplong.any? ? plingplangplong.join : number.to_s
  end

  def inspect_factors(prime_factors)
    (prime_factors & [3,5,7]).map do |prime_factor|
      case prime_factor
      when 3 then 'Pling'
      when 5 then 'Plang'
      when 7 then 'Plong'
      end
    end
  end
end

module PrimeFactors
  # taken from previous exercise
  def self.for(number)
    factor = 2
    return_value = []
    until number < factor
      div, mod = number.divmod(factor)
      if mod == 0
        return_value << factor
        number = div
      else
        factor += 1
      end
    end
    return_value
  end
end
