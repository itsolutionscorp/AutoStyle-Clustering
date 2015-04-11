class Grains
  def initialize
    @grains = Array.new(64)
  end

  def square(number)
    return @grains[number] if @grains[number]
    @grains[number] =  2 ** (number - 1)
  end

  def total
    (1..64).reduce { |acc, num| acc += square(num) }
  end

end
