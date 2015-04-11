class Raindrops

  def self.get_prime_factors(number)
    prime_factors = []
    divisor = 2
    while number > 1
      if number % divisor == 0
        prime_factors << divisor
        number = number / divisor
      else
        divisor += 1
      end
    end
    prime_factors
  end

  def self.convert(number)
    prime_factors = Raindrops.get_prime_factors(number)
    if prime_factors & [3,5,7] == []
      return number.to_s
    else
      string = ''
      prime_factors.uniq.each do |prime_factor|
        case prime_factor
          when 3
            string += 'Pling'
          when 5
            string += 'Plang'
          when 7
            string += 'Plong'
        end
      end
      return string
    end
  end

end
