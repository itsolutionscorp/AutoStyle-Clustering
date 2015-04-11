class Grains


  def square(grain)
    grain = grain - 1
    2**grain
  end

  def total
    chess = Array (1..64)
    chess.inject(0) { |result, element|  result + square(element) }
  end
end
