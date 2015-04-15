class Grains

  def square(n)
    2 ** (n - 1)
  end

  def total
    sum = 0
    for i in 0..63
      sum += 2 ** i
    end
    sum
  end

end
