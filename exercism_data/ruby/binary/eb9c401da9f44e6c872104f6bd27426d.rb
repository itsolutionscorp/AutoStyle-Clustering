class NumberBase
  def self.base(n)
    define_method(:base){ n }
  end

  def initialize(str)
    @str = str
  end

  def to_decimal
    @str.chars.inject(0) {|sum, char| sum * base + Integer(char) }
  rescue ArgumentError
    0
  end
end

class Binary < NumberBase
  base 2
end
