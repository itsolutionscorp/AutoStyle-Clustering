class Grains
  def square(number)
    2**(number - 1)
  end

  def total
    sum = 0
    for number in (1..64) do
      sum += 2**(number - 1)
    end
    sum
  end
end
