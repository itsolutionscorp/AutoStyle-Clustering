class Grains

  RATIO_TO_PREVIOUS_SQUARE = 2
  NUM_SQUARES = 64

  def square(num)
    RATIO_TO_PREVIOUS_SQUARE ** (num - 1)
  end

  def total
    RATIO_TO_PREVIOUS_SQUARE ** NUM_SQUARES - 1
  end

end
