class Grains
  def square(square)
    2 ** (square - 1)
  end

  def total
    sum = 0
    (1..63).each { |num| sum += (2 ** num) }
    sum + 1
  end
end
