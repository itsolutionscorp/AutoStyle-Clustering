class Grains
  def square(num)
    if num == 1
      1
    else
      2 * square(num - 1)
    end
  end

  def total
    (1..64).reduce { |a, e| a + square(e) }
  end
end
