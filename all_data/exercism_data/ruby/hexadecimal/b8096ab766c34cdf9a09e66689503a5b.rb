class Hexadecimal
  def initialize(hex)
    @hex = hex
  end

  def to_decimal
    hex_digits.each_with_index.reduce(0) do |sum, (digit, index)|
      sum + digit.to_i * 16 ** index
    end
  end

private

  def hex_digits
    validate @hex.reverse.chars.map { |c| from_numeric(c) || from_letter(c) }
  end

  def validate(digits)
    digits.any? { |d| d.nil? } ? [] : digits
  end
  
  def from_numeric(numeric)
    numeric[/\d/]
  end

  def from_letter(letter)
    letter.ord - "a".ord + 10 if letter[/[a-f]/]
  end

end
