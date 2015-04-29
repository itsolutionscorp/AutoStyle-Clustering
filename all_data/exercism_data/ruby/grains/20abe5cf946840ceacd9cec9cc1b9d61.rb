class Grains
  def square(number)
    # squares the number by its place on the board
    # could also do a bit-shift because we're working with binary

    return 2**(number-1)
  end

  def total
    #adds each square on the board to arrive at the total
    @@sum = 0
    (1..64).each.map { |i| @@sum += square(i) }
    return @@sum
  end
end
