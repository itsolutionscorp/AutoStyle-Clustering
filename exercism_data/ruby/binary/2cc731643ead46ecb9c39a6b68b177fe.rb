require 'pry'
class Binary

  attr_reader :number

  def initialize(number)
    @number = number
  end

  def to_decimal
    reverse_numbers.each_with_index.inject(0) do |sum, numbers_with_index|
      sum += numbers_with_index.first * 2**numbers_with_index.last
    end
  end

  private

  def binary_number?
    number =~ /^(1|0)+$/
  end

  def reverse_numbers
    @reverse_numbers ||= binary_number? ? number.reverse.split('').map(&:to_i) : []
  end

end
