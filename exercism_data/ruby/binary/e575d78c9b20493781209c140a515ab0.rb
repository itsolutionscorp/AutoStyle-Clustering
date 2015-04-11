require 'pry'
class Binary
  attr_reader :numbers
  def initialize(number)
    @numbers = number.split('')
  end

  def to_decimal
    alphabet = ('a'..'z').to_a
    accumulator = 0
    numbers.reverse.each_with_index do |number, index|
      if alphabet.any? { |letter| letter == number }
        return 0
      else
        accumulator += 2**index * number.to_i
        binding.pry
      end
    end
    accumulator
  end
end
