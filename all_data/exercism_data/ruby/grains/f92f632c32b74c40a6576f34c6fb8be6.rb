class Grains

  def square(int)
    (1..int).inject(1) { |accum, i| accum * 2 } / 2
  end

  def total
    (1..64).map { |i| square(i) }.inject(:+)
  end

end
