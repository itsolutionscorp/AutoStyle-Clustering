class Grains

  MAX_SQUARES = 64

  def square number
   2 ** number.pred
  end

  def total
    ( 1..MAX_SQUARES ).reduce { |total, number| total += square number }
  end

end
