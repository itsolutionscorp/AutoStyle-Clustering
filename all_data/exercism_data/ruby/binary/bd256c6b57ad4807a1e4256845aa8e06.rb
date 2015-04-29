class Binary

  attr_reader :to_decimal

  def initialize(binary)
    @to_decimal = convert(binary)
  end

  def convert(binary)
    return 0 if has_incorrect_characters?(binary)
    conversion = binary.chars.map(&:to_i).zip(binary.length.downto(1).map { |i| 2**(i-1) })
    conversion.map { |i| (i[0] != 0 ? i[1] : 0) }.reduce(:+)
  end

  private
  def has_incorrect_characters?(binary)
    binary.respond_to?(:match) && binary.match(/[^0-1]/)
  end

end
