class Grains

  def initialize
    @sum = 1
  end

  def square(num)
    start = 1
    counter = 1
    while counter < num
      start = start * 2
      counter += 1
      @sum = @sum + start
    end
    start
  end

  def total
    square(64)
    @sum
  end

end
