class Binary
  def initialize(n)
    @n = n
  end

  def to_decimal
    return 0 if @n.match /[^01]/
    find_digit_values.reduce(0) { |sum, digit_value| sum + digit_value }
  end

  private

  def find_digit_values
    m = 0.5
    @n.chars.reverse.map do |digit, i|
      m *= 2
      digit.to_i * m
    end
  end
end