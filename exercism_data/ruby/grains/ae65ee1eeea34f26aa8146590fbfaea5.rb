class Grains
  SQUARE_COUNT = 64

  def initialize
    @cache = []
  end

  def square(num)
    return @cache[num] if @cache[num]
    @cache[num] = (num == 1 ? num : square(num - 1) * 2)
  end

  def total
    (1..64).inject(0){|old, current| old + square(current)}
  end
end
