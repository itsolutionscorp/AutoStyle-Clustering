class Grains
  def square(tile)
    2**(tile-1)
  end

  def total
    (1..64).map{|t| self.class.new.square(t) }.inject(:+)
  end
end
