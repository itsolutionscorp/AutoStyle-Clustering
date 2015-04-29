class Integer
  def to_roman
    RomanConverter.new(self).convert
  end
end

class RomanConverter
  ROMANS = {
       1 => %w[I V X], #    0..9
      10 => %w[X L C], #   10..90
     100 => %w[C D M], #  100..900
    1000 => %w[M M M]  # 1000..3000
  }

  def initialize(decimal)
    raise "Only numbers until 3000 can be converted." if decimal > 3000
    @decimal = decimal
  end

  def convert
    chars = @decimal.to_s.chars.to_a
    length = chars.length
    chars.map.with_index.each_with_object("") do |(char, i), result|
      first_digit = char.to_i
      index = length - i - 1
      result << select_roman(first_digit, ROMANS[10**index])
    end
  end

  def select_roman(first_digit, symbols)
    left, middle, right = symbols
    case first_digit
    when (0..3) then left.to_s * first_digit
    when 4      then [left, middle].join
    when (5..8) then [middle, left.to_s * (first_digit % 5)].join
    when 9      then [left, right].join
    end
  end
end
