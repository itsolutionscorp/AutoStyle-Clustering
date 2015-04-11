class Grains
  def square(square)
    2**(square-1)
  end

  def total
    total = 0
    64.times { |square| total += self.square(square+1) }
    total
  end
end
