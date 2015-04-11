class Raindrops
  def self.convert(number)
    new(number).convert
  end

  def initialize(number)
    @number = number
  end

  def convert
    factors.empty? ? @number.to_s : factors.values.join
  end

  private

  def dictionary
    @dictionary ||= { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  end

  def factors
    @factors ||= dictionary.select(&factor)
  end

  def factor
    @factor ||= ->(prime, _) { @number.modulo(prime).zero? }
  end
end
