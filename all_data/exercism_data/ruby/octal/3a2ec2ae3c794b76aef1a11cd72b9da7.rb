class Octal
  BASE = 8

  def initialize(trinary)
    @digits = trinary.chars.map(&:to_i)
  end

  def to_decimal
    @digits.reverse_each.with_index.inject(0) do |decimal, (digit, exponent)|
      decimal + digit * BASE ** exponent
    end
  end
end
