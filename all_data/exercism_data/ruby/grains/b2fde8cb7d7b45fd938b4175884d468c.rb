class Grains
  def square(pos)
    2**(pos-1)
  end

  def total
    (1..64).to_a.reduce { |total, pos| 2**pos - 1 }
  end
end
