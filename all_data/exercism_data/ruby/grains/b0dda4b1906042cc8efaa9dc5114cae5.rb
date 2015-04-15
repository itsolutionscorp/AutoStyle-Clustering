class Grains

  SQUARES = 64.times.map{ |number| 2**number }
  TOTAL = 18446744073709551615

  def initialize
  end

  def square number
    SQUARES[number-1]
  end

  def total
    TOTAL
  end
end
