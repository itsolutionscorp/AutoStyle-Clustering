class Grains
  TOTAL_NUMBER_OF_SQUARES = 64

  def square(number)
    2**(number-1)
  end

  def total
    (1..TOTAL_NUMBER_OF_SQUARES).reduce {|acc, n| acc += square(n) }
  end

end
