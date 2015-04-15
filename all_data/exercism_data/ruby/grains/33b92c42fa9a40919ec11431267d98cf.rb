class Grains

  def square(num)
    2 ** (num - 1)
  end

  def total
    i = 1
    sum = 0
    64.times do
      sum += square(i)
      i += 1
    end
    return sum
  end

end
