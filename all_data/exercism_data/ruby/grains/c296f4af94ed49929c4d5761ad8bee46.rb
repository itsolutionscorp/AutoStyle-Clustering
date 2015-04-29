class Grains

  def initialize
    @grains = Array.new(64)
  end

  def square(number)
    return @grains[number] if @grains[number]
    return 1               if number == 1
    @grains[number] =  2 * square(number - 1)
  end

  def total
    (1..64).reduce { |acc, num| acc += square(num) }
  end

end
