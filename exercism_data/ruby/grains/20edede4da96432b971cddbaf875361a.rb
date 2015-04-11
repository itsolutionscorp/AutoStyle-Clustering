class Grains
  def square(location)
    2**(location - 1)
  end

  def total
    (1..64).inject { |sum, i| sum + Grains.new.square(i) }
  end
end
