class Octal
  BASE = 8

  def initialize(string)
    @digits = string.chars.map(&:to_i)
  end

  def to_decimal
    @digits.reverse_each.with_index.inject(0) do |decimal, (digit, exponent)|
      decimal + digit * BASE ** exponent
    end
  end
end
