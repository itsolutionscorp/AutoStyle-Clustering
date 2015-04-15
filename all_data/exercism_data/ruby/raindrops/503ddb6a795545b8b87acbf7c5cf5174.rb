require 'Prime'

class Raindrops
  def self.convert(input)
    output = ""

    factors = get_prime_factors(input)

    if factors.include? 3
      output += "Pling"
    end
    if factors.include? 5
      output += "Plang"
    end
    if factors.include? 7
      output += "Plong"
    end

    if output == ""
      input.to_s
    else
      output
    end
  end

  private

  def self.get_prime_factors(input)
    factors = Prime.prime_division(input)
    factors.flat_map { |f| f[0]}
  end
end
