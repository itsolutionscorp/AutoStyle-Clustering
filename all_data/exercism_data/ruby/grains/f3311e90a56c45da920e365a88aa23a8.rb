class Grains
  def square(number)
    double(number)
  end

  def total
    (1..64).reduce {|acc, n| acc += double(n) }
  end

  private

  def double(number)
    (1..number).reduce { |acc| acc = acc * 2}
  end
end
