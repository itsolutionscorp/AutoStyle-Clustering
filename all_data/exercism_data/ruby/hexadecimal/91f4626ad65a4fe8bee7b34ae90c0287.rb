class Hexadecimal
  def initialize(input)
    @digits = input.chars.reverse
  end

  def to_decimal
    return 0 if invalid?
    output = 0
    @digits.each_with_index do |digit, index|
      output += value[digit] * 16**index
    end
    output
  end

  def invalid?
    @digits.join[/[^0-9a-f]/]
  end

  def value
    {
      '0' => 0,
      '1' => 1,
      '2' => 2,
      '3' => 3,
      '4' => 4,
      '5' => 5,
      '6' => 6,
      '7' => 7,
      '8' => 8,
      '9' => 9,
      'a' => 10,
      'b' => 11,
      'c' => 12,
      'd' => 13,
      'e' => 14,
      'f' => 15
    }
  end
end
