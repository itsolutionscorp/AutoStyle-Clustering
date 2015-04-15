class Grains
  def initialize
      @data = Array.new(64)
      @data[0] = 1
  end

  def square(i)
    if i < 65
      if @data[i-1] != nil
        return @data[i-1]
      else
        val = square(i - 1) * 2
        @data[i-1] = val
        return val
      end
    end
  end

  def total
    square(64)
    sum = 0
    for i in @data
      sum += i
    end
    return sum
  end
end
