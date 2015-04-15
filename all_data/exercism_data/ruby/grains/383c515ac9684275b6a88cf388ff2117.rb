class Grains

  def initialize
    @array = Array.new
  end

  def square(square)
    answer = 1
    @array.push(answer)
    (square-1).times do
      answer *= 2
      @array.push(answer)
    end
    answer
  end

  def total
    sum = 0
    square(64)
    @array.each do |each|
      sum += each
    end
    sum
  end
end
