class Trinary
  BASE = 3

  def initialize(number)
    @number = number
  end

  def to_decimal
    digits.map.with_index do |digit, index|
      digit * BASE ** index
    end.reduce(:+)
  end

  private

  def digits
    @number.to_s.chars.map { |digit| digit.to_i }.reverse
  end
end
