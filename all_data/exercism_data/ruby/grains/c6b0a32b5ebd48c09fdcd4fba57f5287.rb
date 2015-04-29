class Grains
  attr_reader :total
  def initialize
    @array = []
    x=1
    @total=0
    64.times do
      @array.push(x)
      @total += x
      x=x*2
    end
  end
  def square(num)
    @array[num-1]
  end
end
