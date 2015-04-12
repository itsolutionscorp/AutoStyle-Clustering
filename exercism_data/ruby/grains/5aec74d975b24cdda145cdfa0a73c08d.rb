class Grains
  def grains_on_square(square_number)
    2**(square_number - 1)
  end

  def total
    (1..64).reduce{ |sum, n| sum + self.grains_on_square(n) }
  end
end