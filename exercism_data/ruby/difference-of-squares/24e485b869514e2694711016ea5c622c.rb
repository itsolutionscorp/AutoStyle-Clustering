class Squares
  def initialize(natural_num)
    @natural_num = natural_num

    if is_float? or is_negative?
      raise ArgumentError, "Natural numbers must be whole and not negative", caller
    end
  end

  def is_float? 
    @natural_num.class.eql?(Float) ? true : false
  end

  def is_negative?
    @natural_num < 0 ? true : false
  end

  def square_of_sums
    (1..@natural_num).inject(:+) ** 2
  end

  def sum_of_squares
    (1..@natural_num).inject{|sum, n| sum += n ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
