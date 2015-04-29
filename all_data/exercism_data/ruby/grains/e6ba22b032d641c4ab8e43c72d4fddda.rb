class Grains

  def square(num)
    grains = 0
    (1..num).each do |n|
      if grains == 0
        grains += 1
      else
        grains *= 2
      end
    end
    return grains
  end
  
  def total
    (1..64).collect { |n| square(n) }.inject(:+)
  end

end
