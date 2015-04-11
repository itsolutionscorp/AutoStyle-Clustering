class Binary
  def initialize(binary_string)
    @digits = binary_string.reverse.chars.map { |c| c.to_i }
  end

  def to_decimal
    @decimal ||= calculate_decimal
  end

  private

  def calculate_decimal
    result = 0
    @digits.each_with_index do |d, i|
      result += d * 2**i
    end
    result
  end
end
