class Grains
  def square(num)
    return 1 if num == 1
    square(num-1)*2
  end

  def total
    sum = 0
    (1..64).each do |n|
      sum += square(n)
    end
    sum
  end
end
