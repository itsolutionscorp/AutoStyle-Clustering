class Grains

  def initialize
    @total_squares = 64
    @spaces = [1]
  end

  def square(num)
    @spaces.first * 2 ** (num - 1)
  end

  def total
    (@spaces.length < @total_squares) ? add_space : sum_of_spaces
  end

  def add_space
    new_space = @spaces.last * 2
    @spaces << new_space
    total
  end

  def sum_of_spaces
    @spaces.inject { |sum, space| sum + space }
  end

end
