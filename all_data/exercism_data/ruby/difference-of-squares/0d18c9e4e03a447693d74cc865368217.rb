class Squares

  def initialize sq
    @sq = sq
  end

  def square_of_sums
    return rec_add(@sq) ** 2
  end

  def sum_of_squares
    return req_pow(@sq)
  end

  def difference
    return (sum_of_squares - square_of_sums).abs
  end

  private
  def rec_add num
    if num == 0
      return 0
    else
      return num + rec_add(num - 1)
    end
  end

  def req_pow num
    if num == 0
      return 0
    else
      return num**2 + req_pow(num-1)
    end
  end

end
