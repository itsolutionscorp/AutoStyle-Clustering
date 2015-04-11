class Grains
  def square(number)
    2 ** (number - 1)
  end

  def total
    total = 0
    for i in (1..64) do
      total += square(i)
    end
    total
  end
end
