class Grains
  @@total_squares = 64

  def square(square_num)
    2 ** (square_num - 1)
  end

  def total
    1.upto(@@total_squares).inject(0) do |sum, n|
      sum += square(n)
    end
  end
end
