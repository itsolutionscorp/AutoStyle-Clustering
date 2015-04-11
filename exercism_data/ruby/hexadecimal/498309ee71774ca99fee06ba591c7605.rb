class Hexadecimal
  attr_reader :hexadecimal

  @@decimal_conversion = { # key = hexadecimal, value = decimal
    '0' => '0',
    '1' => '1',
    '2' => '2',
    '3' => '3',
    '4' => '4',
    '5' => '5',
    '6' => '6',
    '7' => '7',
    '8' => '8',
    '9' => '9',
    'A' => '10',
    'B' => '11',
    'C' => '12',
    'D' => '13',
    'E' => '14',
    'F' => '15'
  }

  def initialize hexadecimal
    @hexadecimal = hexadecimal
  end

  def to_decimal
    return 0 unless valid?

    decimal = 0
    split_and_reverse.each_with_index do |number, index|
      decimal += @@decimal_conversion[number].to_i * (16 ** index)
    end
    decimal
  end

  def split_and_reverse
    hexadecimal.chars.reverse.map(&:upcase)
  end

  def valid?
   split_and_reverse.all? { |number| @@decimal_conversion.keys.include? number }
  end
end
