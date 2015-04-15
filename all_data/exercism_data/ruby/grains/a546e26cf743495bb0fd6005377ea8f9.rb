class Grains
  def square(num)
    2**(num-1)
  end

  def total
    (1..64).reduce(0) do |acc, i|
      acc + square(i)
    end
  end
end
