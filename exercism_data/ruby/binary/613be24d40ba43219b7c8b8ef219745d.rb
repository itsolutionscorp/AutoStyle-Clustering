require 'set'

class Binary
  attr_reader :digits, :valid_digits

  def initialize(binary_string)
    @digits = binary_string.split('')
    @valid_digits = Set.new ['0', '1']
  end

  def to_decimal
    return 0 if digits.any? { |d| non_binary_digit?(d) }

    digits.
      map(&:to_i).
      reverse.
      zip((0...digits.length).to_a).
      map {|p| p[0]*2 ** p[1]}.
      reduce(:+)
  end

  private

  def non_binary_digit?(d)
    !valid_digits.member?(d)
  end
end
