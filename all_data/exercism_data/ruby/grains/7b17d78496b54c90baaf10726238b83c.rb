class Grains
  def initialize
    @total = 1
  end

  def square(index)
    index == 1 ? 1 : count_grain(index)
  end

  def total
    square(64)
    @total
  end

  def count_grain(index)
    grain = 1
    (index - 1).times do
      grain = (grain*2)
      @total = @total + grain
    end
    grain
  end
end
