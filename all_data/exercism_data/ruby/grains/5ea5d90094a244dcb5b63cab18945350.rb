class Grains

  def square(number)
    2**(number-1)
  end

  def total
    (1..64).reduce { |sum, value| sum + self.square(value) }
  end

end
