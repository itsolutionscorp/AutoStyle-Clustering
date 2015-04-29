class Integer
  def to_roman
    RomanConverter.new(self).convert
  end
end

class RomanConverter
  def initialize(decimal)
    raise "Only numbers until 3000 can be converted." if decimal > 3000
    @decimal = decimal
  end

  def convert
    split(@decimal).map do |convertable|
      first_digit = convertable.to_s[0].to_i
      select_roman(first_digit, *borders(convertable))
    end.join
  end

  # Example:
  #   split(1990) #=> [1000, 900, 90, 0]
  def split(number)
    number.to_s.chars.to_a.reverse.map.with_index do |char, index|
      decimal_factor = 10**index
      char.to_i * decimal_factor
    end.reverse
  end

  # Example:
  #   select_roman(0, "I", nil, "V") #=> ""
  #   select_roman(1, "I", nil, "V") #=> "I"
  #   select_roman(2, "I", nil, "V") #=> "II"
  #   select_roman(3, "I", nil, "V") #=> "III"
  #   select_roman(4, "I", nil, "V") #=> "IV"
  #   select_roman(5, "V", "I", "X") #=> "V"
  #   select_roman(6, "V", "I", "X") #=> "VI"
  #   select_roman(7, "V", "I", "X") #=> "VII"
  #   select_roman(8, "V", "I", "X") #=> "VIII"
  #   select_roman(9, "V", "I", "X") #=> "IX"
  def select_roman(digit, left, middle, right)
    [
      left.to_s * 0,
      left.to_s * 1,
      left.to_s * 2,
      left.to_s * 3,
      [left, right].join,
      [left, middle.to_s * 0].join,
      [left, middle.to_s * 1].join,
      [left, middle.to_s * 2].join,
      [left, middle.to_s * 3].join,
      [middle, right].join
    ].fetch(digit)
  end

  def borders(number)
    case number
    when (0..0)       then ["",  nil, "" ]
    when (1..4)       then ["I", nil, "V"]
    when (5..9)       then ["V", "I", "X"]
    when (10..40)     then ["X", nil, "L"]
    when (50..90)     then ["L", "X", "C"]
    when (100..400)   then ["C", nil, "D"]
    when (500..900)   then ["D", "C", "M"]
    when (1000..3000) then ["M", nil, "M"]
    end
  end
end
