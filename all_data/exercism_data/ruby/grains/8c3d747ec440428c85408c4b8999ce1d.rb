class Grains

  @@board_size = 64

  def square(single_square)
    @ss = single_square - 1
    2 ** @ss
  end

  def total
    grain_sum = Array.new(@@board_size) { |e| e = 2 ** e } # populate the array with squared values
    grain_sum.sum
  end

end


class Array
  def sum
    self.inject { |sum, x| sum + x }
  end
end
