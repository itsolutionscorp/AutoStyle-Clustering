class Squares

  public

  def initialize limit
    SquareCalcSingleton.get_instance().set_limit(limit)
  end

  def square_of_sums
    return @square_of_sums =  SquareCalcSingleton.get_instance().square_of_sums
  end

  def sum_of_squares
    return @sum_of_squares = SquareCalcSingleton.get_instance().sum_of_squares
  end

  def difference
    return @sum_of_squares - @square_of_sums
  end

end

class SquareCalcSingleton

  private_class_method :new

  public

  def self.get_instance
    @instance ||= new
  end

  def set_limit limit
    @limit = limit
  end

  def square_of_sums
    result = 0
    @limit.times do |x|
      result += (x+1)
    end
    result ** 2
  end

  def sum_of_squares
    result = 0
    @limit.times do |x|
      result += x * x
    end
  end


end
