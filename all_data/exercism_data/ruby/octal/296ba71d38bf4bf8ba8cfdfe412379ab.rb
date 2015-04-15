class Octal
  def initialize(string)
    @string = string.reverse
  end
  def to_decimal
    sum = 0
    @string.each_char.with_index do |number, index|
      term = number.to_i * (8 ** index)
      sum += term
    end
    sum
  end
end
