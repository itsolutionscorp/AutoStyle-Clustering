class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    1.upto(@n).inject do |result, natural_number|
      result + natural_number
    end**2
  end

  def sum_of_squares
    1.upto(@n).inject do |result, natural_number|
      result + natural_number**2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
