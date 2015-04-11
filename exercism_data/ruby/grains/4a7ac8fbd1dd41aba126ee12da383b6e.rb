class Grains

  def initialize
    @array = []
  end

  def square(num)
    @counter = 1
    @array.push(@counter)
    (num - 1).times do
      @counter *= 2
      @array.push(@counter)
    end
    @counter
  end

  def total
    sum = 0
    square(64)
    @array.each do |x|
      sum += x
    end
    sum
  end
end
