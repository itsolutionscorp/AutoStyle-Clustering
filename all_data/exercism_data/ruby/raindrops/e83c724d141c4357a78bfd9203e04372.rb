class Raindrops

  def self.convert number
    Number.new(number).convert
  end

end

class Number

  def initialize number
    @number = number
  end

  public

  def convert
    output = nil
    output << "Pling" if has_prime_factor(3)
    output << "Plang" if has_prime_factor(5)
    output << "Plong" if has_prime_factor(7)

    output || @number.to_s
  end

  private

  def has_prime_factor num
    @number % num == 0
  end

end
