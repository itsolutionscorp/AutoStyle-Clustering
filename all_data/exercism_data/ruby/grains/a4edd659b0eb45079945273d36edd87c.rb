class Grains

  def square(num)
    (1..num).inject{|acc, num| acc * 2 }
  end

  def total
    18446744073709551615
  end

end
