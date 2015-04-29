class Grains

  def square(num)
    2 **(num -1)
  end

  def total
    (1..64).inject(0) {|sum, n| sum + square(n)}
  end

end
