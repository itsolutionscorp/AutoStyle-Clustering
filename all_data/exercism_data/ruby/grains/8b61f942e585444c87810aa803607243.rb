class Grains
  def square(num)

    num = num - 1
    @grain = 2 ** num
    return @grain
  end

  def total
    grain_total = (2 ** 64) - 1
    return grain_total
  end
end
