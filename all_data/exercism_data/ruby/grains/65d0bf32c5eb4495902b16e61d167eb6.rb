class Grains
  def square(number)
    2 ** (number - 1)
  end

  def total
    sum = 0
    64.times do |number|
      sum += 2 ** number
    end
    sum
  end
end
