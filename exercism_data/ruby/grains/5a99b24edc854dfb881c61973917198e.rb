class Grains

  GRAINS = {}
  (1..64).reduce(1) { |acc, pow| GRAINS[pow] = acc; acc << 1 }

  def square(num)
    GRAINS[num]
  end

  def total
    (square(64) << 1) - 1
  end
end
