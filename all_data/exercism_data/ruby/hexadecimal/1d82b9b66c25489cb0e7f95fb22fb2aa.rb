class Hexadecimal

  EQUIVALENCES = { 
    'a' => 10, 
    'b' => 11,
    'c' => 12,
    'd' => 13,
    'e' => 14,
    'f' => 15
  }

  attr_reader :hexadecimal_string

  def initialize hexadecimal_string
    @hexadecimal_string = hexadecimal_string
  end

  def to_decimal
    if valid_hexadecimal?
      sum 
    else
      0
    end
  end

private

  def sum
    conversion.reduce:+
  end

  def conversion 
    array_of_strings.each_with_index.map do |digit, index|
      digit_conversion( digit ) * 16 ** index 
    end
  end
  
  def array_of_strings
    hexadecimal_string.reverse.chars
  end

  def digit_conversion digit
    if EQUIVALENCES.has_key? digit
      EQUIVALENCES[ digit ]
    else
      digit.to_i  
    end
  end

  def valid_hexadecimal?
    !( hexadecimal_string =~ /[^abcdef\d]/ )
  end

end
