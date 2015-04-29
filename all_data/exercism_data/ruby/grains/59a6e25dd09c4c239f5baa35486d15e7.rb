class Grains
  def square(num)
    num == 1 ? 1 : 2 * square(num - 1)
  end

  def total
    (1..64).inject { |total, num| total + square(num) }
  end
end
