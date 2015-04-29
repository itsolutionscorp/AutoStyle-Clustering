class Grains

  def square(n)
    recursive_square @@starting_value, 1, n
  end

  def total
    recursive_total 0, @@chessboard_size
  end

private
  @@starting_value = 1
  @@chessboard_size = 64

  def recursive_square(current_value, current_square, to_square)
    if (current_square > 1)
      current_value *= 2
    end
    if (current_square < to_square)
      current_value = recursive_square current_value, current_square + 1, to_square
    end
    return current_value
  end

  def recursive_total(total, current_square)
    total += square current_square
    if current_square > 1
      total = recursive_total total, current_square - 1
    end
    return total
  end

end
