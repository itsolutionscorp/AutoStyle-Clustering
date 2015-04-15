class Grains

  def square(number)
    2**(number - 1)
  end

  def total
    (1..64).reduce(0) do |sum, square|
      sum + square(square)
    end
  end

end
