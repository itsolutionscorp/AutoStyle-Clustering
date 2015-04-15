class Squares
  def initialize(n)
    @n = n
  end

  def toArray(n)
    if n.last - 1 == 0
      n
    else
      toArray(n.push n.last - 1)
    end
  end

  def square_of_sums
    a = toArray([@n])
    a.reduce(0) {|x, y|
      x + y
    }**2
  end

  def sum_of_squares
    a = toArray([@n])
    a.reduce(0) {|x, y|
      x + y**2
    }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
