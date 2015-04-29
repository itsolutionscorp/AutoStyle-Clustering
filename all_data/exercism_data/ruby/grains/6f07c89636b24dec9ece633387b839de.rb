class Grains
  SQUARES = (1..64)

  def square(square_id)
    2**(square_id - 1)
  end

  def total(range = SQUARES)
    range.reduce { |subtotal, square_id| subtotal + square(square_id) }
  end
end
