class Grains

  def square(x)
    2**(x - 1)
  end

  def total
    (1..64).inject(0) do |a, e|
      a += square(e)
    end
  end

end
