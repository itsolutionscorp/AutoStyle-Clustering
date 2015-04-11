class Grains
  attr_reader :start_qty, :multiplier, :total_squares

  def initialize(start_qty = 1, multiplier = 2, total_squares = 64)
    @start_qty = start_qty
    @multiplier = multiplier
    @total_squares = total_squares
  end

  def square(number)
    grains = start_qty
    (number - grains).times { grains = grains * multiplier }
    grains
  end

  def total
    (1..total_squares).inject(0) { |grains, number| grains += square(number) }
  end
end
