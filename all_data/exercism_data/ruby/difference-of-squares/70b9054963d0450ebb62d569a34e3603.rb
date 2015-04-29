class Squares

  def initialize magic_number
    @magic_number = magic_number
  end

  def square_of_sums
    run_loop 1, 2
  end

  def sum_of_squares
    run_loop 2, 1
  end

  def difference
    square_of_sums - sum_of_squares
  end
private

  def run_loop exponent_each, exponent_final
    result = 0
    (1..@magic_number).each do |number|
      result += number**exponent_each
    end
    result**exponent_final
  end
end
