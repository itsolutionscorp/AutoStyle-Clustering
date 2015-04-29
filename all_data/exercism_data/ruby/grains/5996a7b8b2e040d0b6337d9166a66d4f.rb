class Grains
  # optimized for speed
  @@squares = (0..63).collect {|x| 2**x}

  def initialize
  end

  def square(i)
    return @@squares[i - 1]
  end

  def total
    @@squares[0..63].reduce(:+)
  end
end
