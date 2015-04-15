class Grains
  def square(num, grains = 1)
    return grains if num <= 1
    square(num - 1, grains *= 2 )
  end

  def total
    total_grains = 0
    num = 1
    until num > 64
      total_grains += square(num)
      num += 1
    end
    total_grains
  end
end
