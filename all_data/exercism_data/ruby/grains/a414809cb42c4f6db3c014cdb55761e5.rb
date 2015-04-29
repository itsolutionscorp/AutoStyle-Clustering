class Grains

  TOTAL_SQUARES = 64

  def square(count)
    2 ** (count-1)
  end

  def total
    (1..TOTAL_SQUARES).reduce{ |sum,space| sum + square(space) }
  end

end
