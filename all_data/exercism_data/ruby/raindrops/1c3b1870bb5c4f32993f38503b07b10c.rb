class Raindrops

  def self.convert(number)
    prime_factors = []
    result_string = ''
    while (number != prime_factors.inject {|product, prime| product * prime })
      result = find_first_prime_factor(number / prime_factors.inject {|product, prime| product * prime })
      prime_factors << result
    end
    if prime_factors.include?(3)
      result_string = 'Pling'
    end
    if prime_factors.include?(5)
      result_string += 'Plang'
    end
    if prime_factors.include?(7)
      result_string += 'Plong'
    end
    if result_string == ''
      result_string = number.to_s
    end
    result_string
  end

  def self.find_first_prime_factor(number)
    return if number == 1
    possible_prime = 2
    while number % possible_prime != 0
      possible_prime += 1
    end
    possible_prime
  end


end
