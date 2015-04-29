class Raindrops

  def self.convert(number)
    prime_factors = get_prime_factors(number)

    result = ''
    result += 'Pling' if prime_factors.include?(3)
    result += 'Plang' if prime_factors.include?(5)
    result += 'Plong' if prime_factors.include?(7)

    result.empty? ? number.to_s : result
  end

  def self.get_prime_factors(number)
    factors = []
    factor = 2

    while factor <= number
      if number % factor == 0
        number = number / factor
        factors << factor
      else
        factor += 1
      end
    end

    factors
  end

end
