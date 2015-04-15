class Prime

  def initialize(number)
    @number = number
  end

  def prime_factor_of_3?
    @number % 3 == 0
  end

  def prime_factor_of_5?
    @number % 5 == 0
  end

  def prime_factor_of_7?
    @number % 7 == 0
  end

  def not_prime?
    @number % 3 != 0 && @number % 5 != 0 && @number % 7 != 0
  end

end

class Raindrops

  def self.convert(number)
    string = ""
    convert = Prime.new(number)
    if convert.prime_factor_of_3?
      string += "Pling"
    end
    if convert.prime_factor_of_5?
      string += "Plang"
    end
    if convert.prime_factor_of_7?
      string += "Plong"
    end
    if convert.not_prime?
      string += number.to_s
    end
    string
  end

end
