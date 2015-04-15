class Grains
  def square(tile)
    2 ** (tile - 1)
  end

  def total
    (1..64).inject(0) do |sum, tile|
      sum + square(tile)
    end
  end
end
