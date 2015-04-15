class Grains
  def square(position)
    position == 1 ? 1 : square(position - 1) * 2
  end
  
  def total
    grains_per_square = SQUARES.map { |square| self.square(square) }
    grains_per_square.inject(&:+)
  end
  
  private
  
  SQUARES = 1..64
end
