class Grains
  def square square_number
    2 << (square_number - 2)
  end

  def total
    (1..64).map {|num| square num }.reduce(&:+)
  end
end
