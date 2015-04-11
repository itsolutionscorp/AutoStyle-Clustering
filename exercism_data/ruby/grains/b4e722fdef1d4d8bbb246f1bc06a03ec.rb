class Grains

  def square(num)
    square = 1
    grain = 1
    (num-1).times do 
      square += 1
      grain *= 2
    end
    grain
  end

  def total
    (1..64).map { |n| square(n) }.inject(:+)
  end

end
