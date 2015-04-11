class Grains

  def square n
    2**(n-1)
  end

  def total
    # Since powers of 2 is a superset the next one up is one
    # less than the sum of all previous in the set.
    # This is how binary works 1000 is one more than 0111
    square(65) - 1
  end

end
