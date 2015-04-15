class Grains
  def initialize
    @starting_grains = 1
  end

  def square(num)
    return @starting_grains if num == 1
    Grains.new.square(num - 1) * 2
  end

  def total
    total = 0
    (1..64).each do |g|
      total += Grains.new.square(g)
    end
    total
  end
end
