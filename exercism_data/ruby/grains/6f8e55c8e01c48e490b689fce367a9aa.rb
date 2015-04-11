class Grains
  TOTAL_SQUARES = 64
  START_COUNT = 1

  def square(number)
    return START_COUNT if number == START_COUNT
    squares_on_field_before = square(number - 1)
    squares_on_field_before * 2
  end

  def total
    square(TOTAL_SQUARES + 1) - START_COUNT
  end
end
