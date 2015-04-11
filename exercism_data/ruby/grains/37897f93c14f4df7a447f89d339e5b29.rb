class Grains

  @@squares = [1]

  def square(n)
    @@squares[n - 1] ||= 2 * square(n - 1)
  end

  def total
    square(64)
    @@squares.inject {|total, n| total += n}
  end

end
