class Grains

  def square(square)
    answer = 1
    (square-1).times do
      answer *= 2
    end
    answer
  end

  def total
    answer = 1
    64.times do
      answer *= 2
    end
    answer - 1
  end
end
