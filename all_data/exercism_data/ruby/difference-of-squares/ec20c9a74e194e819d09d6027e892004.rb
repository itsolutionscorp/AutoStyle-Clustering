class Squares < Struct.new(:value)

  class SquarableNumber < Struct.new(:value, :cache)    
    def square
      value * value
    end 
  end

  class SummableArray < Struct.new(:ary)
    def sum
      ary.reduce(0){|memo, number| memo += number}
    end
  end

  def square_of_sums
    sum_of_numbers = SummableArray.new((1..value).to_a).sum
    SquarableNumber.new(sum_of_numbers).square
  end
  
  def sum_of_squares
    array_of_squares = (value + 1).times.map{|i| SquarableNumber.new(i).square }
    SummableArray.new(array_of_squares).sum
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
