class Grains
  SQUARE_COUNT = 64

  def square(i)
    2 ** (i-1)
  end

  def total
    output = 0

    SQUARE_COUNT.times do |x|
      output += square(x+1)
    end

    output
  end
end
