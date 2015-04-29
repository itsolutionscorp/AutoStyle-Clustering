class Grains

  def square(num)
    2 ** (num - 1)
  end

  def total
    total_grains = 0
    (1..64).each do |i|
      total_grains += square(i)
    end
    total_grains
  end

end
