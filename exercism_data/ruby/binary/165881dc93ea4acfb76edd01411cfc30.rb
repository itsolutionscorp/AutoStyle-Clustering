require 'pry'
class Binary
  attr_reader :binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    if binary?
      to_sum = 0
      digits = binary.chars.reverse.map(&:to_i)
      digits.each_with_index do |digit, i|
        to_sum += digit*(2**i)
      end
      to_sum
    else
      0
    end
  end

  private

  def binary?
    binary.match(/^[0|1]+$/)
  end
end
