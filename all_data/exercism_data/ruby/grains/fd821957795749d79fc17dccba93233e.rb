class Grains
  def square(position)
    2**(position-1)
  end

  def total
    ((2**65)-1)/2
  end
end
