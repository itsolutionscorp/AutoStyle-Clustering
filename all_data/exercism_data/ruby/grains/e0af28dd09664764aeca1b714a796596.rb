class Grains
  def square n
    2 ** (n - 1)
  end

  def total
    # (1..64).inject {|total, n| total + square(n) }
    running_total 64
  end

  private

  def running_total n
    (2 ** n) - 1
  end
end
