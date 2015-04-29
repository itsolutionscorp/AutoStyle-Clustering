class Grains

  def total
    (0...64).map {|x| 2 ** x}.inject {|acc, x| acc + x}
  end

  def square n
    (0...n).map {|x| 2 ** x}.last
  end

end
