class Grains
  def square(num)
    if num == 1
      num
    else
      2 * square(num - 1)
    end
  end

  def total
    (1..64).inject(0) { |sum, n| sum + square(n) }
  end
end
