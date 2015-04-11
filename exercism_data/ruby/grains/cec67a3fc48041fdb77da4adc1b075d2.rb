class Grains

  def initialize
    @spaces = [0, 1]
  end

  def square space_number
    @spaces << @spaces.last * 2 until @spaces[space_number]
    @spaces[space_number]
  end

  def total
    square 64 and @spaces.inject(:+)
  end
end
