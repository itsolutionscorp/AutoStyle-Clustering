class Grains
  def square(position)
    2**(position-1)
  end

  def total
    (2**64)-1
  end
end
