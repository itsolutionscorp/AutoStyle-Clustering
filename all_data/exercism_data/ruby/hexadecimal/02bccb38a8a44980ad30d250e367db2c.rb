class Hexadecimal
  DIGITS = "0123456789abcdef"

  def initialize(from_string)
    @from_string = from_string
  end

  def to_decimal
    to_s.reverse.chars.each_with_index.map { |char, i|
      DIGITS.index(char) * DIGITS.length**i
    }.inject(0, :+)
  end

  private

  def to_s
    if @from_string.chars.all? { |char| DIGITS.include?(char) }
      @from_string
    else
      ""
    end
  end
end
