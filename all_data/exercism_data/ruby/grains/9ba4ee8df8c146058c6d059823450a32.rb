class Grains
  def square(number)
    (1..number).inject {|result, n| result * 2}
  end

  def total
    square(65) - 1
  end
end
