class Grains
  NUM_SQUARES = 64

  def square(index)
    2 ** (index - 1)
  end

  def total
    (2 ** NUM_SQUARES) - 1
  end
end
