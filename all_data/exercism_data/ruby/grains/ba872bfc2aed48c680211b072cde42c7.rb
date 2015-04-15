class Grains
  TOTAL_SQUARES = 64
  START_COUNT = 1

  def square(number)
    return START_COUNT if number == START_COUNT
    2 ** number - 1
  end

  def total
    square(TOTAL_SQUARES + 1) - START_COUNT
  end
end
