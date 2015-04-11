class NumberBase
  class << self
    def base(n)
      define_method(:base) { n }
    end
  end

  def initialize(string)
    @string = string
  end

  def to_decimal
    place_values.zip(base_powers).inject(0) {|sum, (a, b)| sum + a * b }
  end

  private
  attr_reader :string

  def base_powers
    (0..Float::INFINITY).lazy.map {|n| base ** n }
  end

  def place_values
    string.reverse.chars.map(&:to_i)
  end
end

class Trinary < NumberBase
  base 3
end
