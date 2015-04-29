class Grains
  SQUARE_COUNT = 64

  def square(num)
    2 ** (num - 1)
  end

  def total
    (1..64).inject(0){|old, current| old + square(current)}
  end
end
