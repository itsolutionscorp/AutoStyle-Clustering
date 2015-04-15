class Grains
  attr_reader :grains

  def initialize
    @grains = calculate_grains
  end

  def square(which)
    @grains[which]
  end

  def calculate_grains
    grains = {}
    1.upto(64) do |index|
      if index == 1
        grains[index] = 1
      else
        grains[index] = 2 ** (index - 1)
      end
    end
    grains
  end

  def total
    @grains.values.inject(:+)
  end

end
