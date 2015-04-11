class Grains

  def square number
    number == 1 ? 1 : 2**(number-1)
  end

  def total
    (1..64).inject { |memo,n| memo += square(n)}
  end

end
