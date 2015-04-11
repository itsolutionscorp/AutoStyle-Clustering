class Grains
  def square(number)
    2**(number-1)
  end

  def total
    (1..64).inject { |total, num| total += self.square(num) }
  end

end
