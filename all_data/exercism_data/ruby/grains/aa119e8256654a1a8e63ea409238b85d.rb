class Grains

  def square(num)
    (1..num-1).inject(1) do |sum, n|
      sum * 2
    end
  end

  def total
    (1..64).inject(0) do |sum, n|
      square(n) + sum
    end
  end
end
