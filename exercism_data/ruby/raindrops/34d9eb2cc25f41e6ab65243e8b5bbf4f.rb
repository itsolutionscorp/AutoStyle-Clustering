class Raindrops
  # The mutable version. Methods mutate the object (but at least no one can
  # change the object from the outside)
  def self.convert(number)
    Raindrops.new(number).pling.plang.plong.number_if_empty
  end

  def number_if_empty
    @word.empty? ? number.to_s : @word
  end

  private

  def initialize(number)
    @number = number
    @word = ""
  end

  { pling: 3, plang: 5, plong: 7 }.each_pair do |symbol, factor|
    define_method(symbol) { sound(factor, symbol.to_s.capitalize) }
  end

  def sound(factor, sound)
    tap { @word << sound if divisible_by?(factor) }
  end

  def divisible_by?(divisor)
    (number % divisor).zero?
  end

  attr_reader :number
end
