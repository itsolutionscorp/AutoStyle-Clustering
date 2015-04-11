require 'pry'

# class that allows you figure out the sum
# of multiples up to a certain number
class SumOfMultiples

  def initialize(*numbers)
    @multiples = numbers
  end

  def self.to(num)
    SumOfMultiples.new(3, 5).to(num)
  end

  def to(num)
    result = 0

    (1...num).each do |number|
      result += number if @multiples.select { |multiple| (number % multiple).zero? }.any?
    end

    result
  end
end
