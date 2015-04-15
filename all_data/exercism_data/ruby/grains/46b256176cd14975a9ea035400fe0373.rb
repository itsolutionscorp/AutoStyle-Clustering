class Grains
  def square(index)
    2**(index-1)
  end

  def total
    # In mathematics, the sum from k=1 to n of 2^(k-1) equals 2^n-1
    square(65)-1
  end
end
