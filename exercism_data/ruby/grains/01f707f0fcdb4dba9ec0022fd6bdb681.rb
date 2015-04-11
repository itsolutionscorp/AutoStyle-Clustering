class Grains
  def square(s)
    2 ** (s - 1)
  end
  def total
    t = 0
    [*1..64].each do |s|
      t += square(s)
    end
    t
  end
end
