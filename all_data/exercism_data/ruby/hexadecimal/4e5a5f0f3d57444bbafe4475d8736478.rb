class Hexadecimal
  def initialize hex
    @hexadecimal = clean_hexastring hex
  end
  
  def to_i
    to_decimal
  end
  
  def to_decimal
    @decimal ||= hex_digits.reduce(0) do |sum, digit|
      sum = sum*16 + hex_value(digit)
    end
  end
  
  private
  attr_reader :hexadecimal
  VALID_CHARACTERS = "0123456789abcdef".freeze
  
  def hex_digits
    hexadecimal.chars
  end
  
  def clean_hexastring hex
    valid_hexadecimal?(hex) ? hex.downcase : "0"
  end
  
  def hex_value digit
    VALID_CHARACTERS.index(digit) || 0
  end
  
  def valid_hexadecimal? string
    string =~ /^[#{VALID_CHARACTERS}]+$/i
  end
end
