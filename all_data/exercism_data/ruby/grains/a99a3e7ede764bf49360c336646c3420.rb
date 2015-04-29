class Grains
  def square(square_number)
    2**(square_number - 1)
  end

  def total
    (1..64).reduce{ |sum, n| sum + self.square(n) }
  end
end
