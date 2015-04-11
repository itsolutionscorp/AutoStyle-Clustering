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
    "PlingPlangPlong" if  has_prime_factor(3) && has_prime_factor(5) && has_prime_factor(7)
    "PlingPlang" if  has_prime_factor(3) && has_prime_factor(5)
    "Pling" if  has_prime_factor(3)
    @number.to_s
  end

  private

  def has_prime_factor num
    @number % num == 0
  end

end
