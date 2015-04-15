class Grains

  def square(sq)
    2 ** (sq - 1)
  end

  def total
    64.times.inject(0) { |result, sq| result += square(sq+1) }
  end
 
end
