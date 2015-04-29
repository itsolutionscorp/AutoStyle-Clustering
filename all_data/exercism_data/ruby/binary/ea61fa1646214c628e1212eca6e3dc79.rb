class NumberBase
  def self.base(n)
    define_method(:base){ n }
  end

  def initialize(str)
    @str = str
  end

  def to_decimal
    place_values.zip(base_powers).inject(0) {|sum, (a, b)| sum + a * b }
  end

  private

  attr_reader :str

  def base_powers
    (0..Float::INFINITY).lazy.map {|n| base ** n }
  end

  def place_values
    str.reverse.chars.map(&method(:Integer))
  rescue ArgumentError
    [0]
  end
end


class Binary < NumberBase
  base 2
end
