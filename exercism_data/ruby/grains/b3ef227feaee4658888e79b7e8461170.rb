class Grains

  def square(number)
    grains = 1
    count = 1
    until count == number
      grains *= 2
      count += 1
    end
    grains
  end

  def total
    [*1..64].map { |num| square(num) }.inject(:+)
  end

end
