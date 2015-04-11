class Grains
  def square(position)
    2**(position-1)
  end

  def total 
    (1..64).reduce(0) { |sum, n|
      sum+=square(n)
    }
  end
end
