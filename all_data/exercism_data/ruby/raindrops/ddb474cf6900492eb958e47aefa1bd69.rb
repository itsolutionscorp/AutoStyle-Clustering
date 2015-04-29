class Raindrops
  def self.convert number
    new(number).convert
  end

  def initialize number
    @number = number
  end

  def convert
    result = check_and_convert_prime_3 + check_and_convert_prime_5 + check_and_convert_prime_7
    if result == ""
      @number.to_s
    else
      result
    end
  end

  def check_and_convert_prime_3
    @number % 3 == 0 ? "Pling" : ""
  end

  def check_and_convert_prime_5
    @number % 5 == 0 ? "Plang" : ""
  end

  def check_and_convert_prime_7
    @number % 7 == 0 ? "Plong" : ""
  end
end
