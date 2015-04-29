class Grains

  # Returns the sum of grains on each square.
  def square num
    2**(num-1)
  end

  # Returns the total sum of grains on the chessboard
  def total
    sum = 0
    64.times do |counter|
      sum += (2**counter)
    end
    sum
  end
end
