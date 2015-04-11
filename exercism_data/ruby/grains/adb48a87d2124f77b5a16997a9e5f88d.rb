class Grains
  def square(num)
    grain_count = 1
    return grain_count if num == 1
    (num-1).times { grain_count *= 2}
    return grain_count
  end

  def total
    total = 1
    grain_count = 1
    2.upto(64) { |n| total += square(n) }
    return total
  end
end
