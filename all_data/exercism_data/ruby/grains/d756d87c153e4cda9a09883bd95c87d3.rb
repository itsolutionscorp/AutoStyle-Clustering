class Grains
  def square(index)
    return 2**(index-1)
  end

  def total
    rice = 0
    (1..64).each { |i| rice = rice + self.square(i)}
    rice
  end
end
