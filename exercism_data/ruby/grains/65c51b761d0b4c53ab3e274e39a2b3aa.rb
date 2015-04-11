class Grains
  def initialize
    build_grains
  end
  def square(id)
    @grains[id]
  end
  def total
    @grains.values.reduce(:+)
  end
  def build_grains
    @grains = (2..64).each_with_object({1 => 1}) do |n, obj| 
      obj[n] = obj[n-1] * 2
    end
  end
end
