module Roman
  def to_roman
    encoded_digits_by_position.map { |number| number_to_roman_character[number] }.join
  end

protected

  def encoded_digits_by_position
    number, position, output = self, 1, []
    while number > 0
      output << encode_roman_digit(number % 10, position)
      position *= 10
      number /= 10
    end
    output.reverse.flatten
  end

  def encode_roman_digit(digit, pos)
    digit_breakdown = [[], [1], [1,1], [1,1,1], [1,5], [5], [5,1], [5,1,1], [5,1,1,1], [1,10]]
    digit_breakdown[digit].map { |x| x * pos }
  end

  def number_to_roman_character
    {
      1 => "I",
      5 => "V",
      10 => "X",
      50 => "L",
      100 => "C",
      500 => "D",
      1000 => "M"
    }
  end
end

class Numeric
  include Roman
end
