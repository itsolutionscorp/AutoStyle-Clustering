class Grains
  def initialize
    @starting_grains = 1
  end

  def square(num)
    return @starting_grains if num == 1
    square(num - 1) * 2
  end

  def total
    total_so_far(64)
  end

  def total_so_far(num)
    return 1 if num == 1
    total_so_far(num - 1) + square(num)
  end
end
