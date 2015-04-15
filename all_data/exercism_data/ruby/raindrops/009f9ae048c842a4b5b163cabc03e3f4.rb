class Raindrops
  def self.convert(number)
    result = ""
    result += "Pling" if self.has_prime_factor_3?(number)
    result += "Plang" if self.has_prime_factor_5?(number)
    result += "Plong" if self.has_prime_factor_7?(number)
    return number.to_s if result == ""
    result
  end

  private

  def self.has_prime_factor_3?(number)
    self.divides_by(number, 3)
  end

  def self.has_prime_factor_5?(number)
    self.divides_by(number, 5)
  end

  def self.has_prime_factor_7?(number)
    self.divides_by(number, 7)
  end

  def self.divides_by(number, divisor)
    number % divisor == 0
  end

end
