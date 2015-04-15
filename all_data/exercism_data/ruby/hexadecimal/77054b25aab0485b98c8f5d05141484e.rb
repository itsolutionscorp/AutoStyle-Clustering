class Hexadecimal
  def initialize(hex)
    @hex = hex
  end

  def to_decimal
    digits = @hex.reverse.chars.map { |c| from_digit(c) || from_letter(c) }
    return 0 if digits.any? { |d| d.nil? }
    digits.each_with_index.reduce(0) do |sum, (digit, index)|
      sum + digit.to_i * 16 ** index
    end
  end

private
  
  def from_digit(digit)
    digit[/\d/]
  end

  def from_letter(letter)
    letter.ord - "a".ord + 10 if letter[/[a-f]/]
  end

end
