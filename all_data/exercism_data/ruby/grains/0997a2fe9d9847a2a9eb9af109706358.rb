class Grains

  def square (argument)
    2**(argument - 1)
  end

  def total
    # 18446744073709551615
    grains_total = 0
    count = 1
    until count == 65
      grains_total += self.square(count)
      count += 1
    end 
    grains_total
  end

end
