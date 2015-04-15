class Grains

  def square(num)
    return num if num <= 2
    i = 1
    result = 1
    while i < num
      result = result * 2
      i += 1
    end
    result
  end

  def total
    result = 0
    (1..64).each { |n| result += square(n)}
    result
  end
end
