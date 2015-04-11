class NumberBase
  def self.base(n)
    define_method(:base) { n }
  end

  def initialize(string)
    @string = string
  end

  def to_decimal
    @string.chars.inject(0) {|sum, char| sum * base + Integer(char) }
  rescue ArgumentError
    0
  end
end

class Trinary < NumberBase
  base 3
end
