class Grains
  def square(number_of_squares)
    2 ** (number_of_squares - 1)
  end

  def total
    (0...64).to_a.square!.inject(:+)
  end

end

class Array
  def square!
    self.each_with_index.map {|num, index| 2 ** index}
  end
end
